package com.bytehonor.sdk.framework.server.scheduler.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.string.SpringString;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.scheduler.work.lock.SpringWorkLocker;

/**
 * 集群模式，一个server独占一个group
 * 
 * @author lijianqiang
 *
 */
@Deprecated
public class ClusterGroupExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 5;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delays;
    private final long intervals;
    private final long locks;

    private final List<ClusterGroup> groups;

    private String server;
    private SpringWorkLocker locker;
    private String subject;

    public ClusterGroupExecutor() {
        this(DELAYS, INTERVALS);
    }

    public ClusterGroupExecutor(long delays, long intervals) {
        Java.requireNonNull(server, "server");
        Java.requireNonNull(locker, "locker");
        this.delays = delays;
        this.intervals = intervals;
        this.locks = intervals * 2;
        this.groups = new ArrayList<ClusterGroup>();
        this.subject = "";
    }

    public void start(String server, SpringWorkLocker locker) {
        Objects.requireNonNull(server, "server");
        Objects.requireNonNull(locker, "locker");
        
        LOG.info("server:{} start", server);
        
        if (SpringString.isEmpty(server)) {
            throw new RuntimeException("invalid server:" + server);
        }

        this.server = server;
        this.locker = locker;
        
        if (CollectionUtils.isEmpty(groups)) {
            LOG.warn("works empty");
            return;
        }

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                process();
            }

        }, delays, intervals);
    }

    public ClusterGroupExecutor add(ClusterGroup group) {
        Java.requireNonNull(group, "group");

        LOG.info("add subject:{}", group.subject());

        if (SpringString.isEmpty(group.subject()) == false) {
            groups.add(group);
        }

        return this;
    }

    private void process() {
        try {
            competeWork();
            keepAlive();
            checkIdle();
        } catch (Exception e) {
            LOG.error("run error", e);
        }

    }

    private void competeWork() {
        if (SpringString.isEmpty(subject) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(groups)) {
            LOG.warn("groups empty");
            return;
        }

        for (ClusterGroup group : groups) {
            if (locker.lock(group.subject(), server, locks) == false) {
                continue;
            }

            // 只启动一次
            gotAndStart(group);
            break;
        }
    }
    
    private void gotAndStart(ClusterGroup group) {
        subject = group.subject();
        LOG.info("doStart subject:{}", subject);
        group.factory().run();
    }
    
    private void keepAlive() {
        if (SpringString.isEmpty(subject)) {
            LOG.debug("server:{} keepAlive end, subject empty", server);
            return;
        }

        String which = locker.which(subject);
        boolean success = Java.equals(which, server);
        LOG.info("server:{} keepAlive success:{}, subject:{}", server, success, subject);
        if (success) {
            locker.expireAt(subject, System.currentTimeMillis() + locks);
        }
    }

    private void checkIdle() {
        if (CollectionUtils.isEmpty(groups)) {
            return;
        }

        for (ClusterGroup group : groups) {
            if (SpringString.isEmpty(locker.which(group.subject()))) {
                LOG.warn("server:{} checkIdle subject:{} no worker", server, group.subject());
            }
        }
    }
}
