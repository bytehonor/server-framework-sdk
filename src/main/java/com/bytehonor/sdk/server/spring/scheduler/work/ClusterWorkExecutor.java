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
 * 集群模式，一个server独占一个work
 * 
 * @author lijianqiang
 *
 */
public class ClusterWorkExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterWorkExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 6;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delayMillis;
    private final long intervalMillis;
    private final long lockMillis;

    private final String server;
    private final SpringWorkLocker locker;
    private final List<ClusterWork> works;

    private String subject;

    public ClusterWorkExecutor(String server, SpringWorkLocker locker) {
        this(server, locker, DELAYS, INTERVALS);
    }

    public ClusterWorkExecutor(String server, SpringWorkLocker locker, long delayMillis, long intervalMillis) {
        Java.requireNonNull(server, "server");
        Java.requireNonNull(locker, "locker");
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
        this.lockMillis = intervalMillis * 2;
        this.server = server;
        this.locker = locker;
        this.works = new ArrayList<ClusterWork>();
        this.subject = "";
    }

    public void start() {
        if (CollectionUtils.isEmpty(works)) {
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

    public ClusterWorkExecutor add(ClusterWork work) {
        Java.requireNonNull(work, "work");

        LOG.info("add subject:{}", work.subject());

        if (SpringString.isEmpty(work.subject()) == false) {
            works.add(work);
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

        if (CollectionUtils.isEmpty(works)) {
            LOG.warn("works empty");
            return;
        }

        for (ClusterWork work : works) {
            if (locker.lock(work.subject(), server, lockMillis) == false) {
                continue;
            }

            // 只启动一次
            doWork(work);
            break;
        }
    }
    
    private void doWork(ClusterWork work) {
        try {
            subject = work.subject();
            List<SpringWorkTask> tasks = work.tasks();
            LOG.info("doWork subject:{}, tasks:{}", subject, tasks.size());
            for (SpringWorkTask task : tasks) {
                task.start();
            }
        } catch (Exception e) {
            LOG.error("doWork error", e);
        }
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
        if (CollectionUtils.isEmpty(works)) {
            return;
        }

        for (ClusterWork work : works) {
            if (SpringString.isEmpty(locker.which(work.subject()))) {
                LOG.warn("server:{} checkIdle subject:{} no worker", server, work.subject());
            }
        }
    }
}
