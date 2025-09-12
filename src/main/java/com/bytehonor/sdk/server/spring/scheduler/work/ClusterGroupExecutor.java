package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

/**
 * 集群模式，一个server独占一个group
 * 
 * @author lijianqiang
 *
 */
public class ClusterGroupExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 6;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delayMillis;
    private final long intervalMillis;
    private final long lockMillis;

    private final String server;
    private final SpringWorkLocker locker;
    private final List<ClusterGroup> groups;

    private String subject;

    public ClusterGroupExecutor(String server, SpringWorkLocker locker) {
        this(server, locker, DELAYS, INTERVALS);
    }

    public ClusterGroupExecutor(String server, SpringWorkLocker locker, long delayMillis, long intervalMillis) {
        Java.requireNonNull(server, "server");
        Java.requireNonNull(locker, "locker");
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
        this.lockMillis = intervalMillis * 2;
        this.server = server;
        this.locker = locker;
        this.groups = new ArrayList<ClusterGroup>();
        this.subject = "";
    }

    public void start() {
        if (CollectionUtils.isEmpty(groups)) {
            LOG.warn("works empty");
            return;
        }
        LOG.info("server:{} start", server);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                process();
            }

        }, delayMillis, intervalMillis);
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
            if (locker.lock(group.subject(), server, lockMillis) == false) {
                continue;
            }

            // 只启动一次
            doStart(group);
            break;
        }
    }
    
    private void doStart(ClusterGroup group) {
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
            locker.expireAt(subject, System.currentTimeMillis() + lockMillis);
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
