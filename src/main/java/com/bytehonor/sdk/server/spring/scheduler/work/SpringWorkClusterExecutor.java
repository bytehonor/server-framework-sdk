package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

/**
 * 集群承担任务，单点独占一个任务
 * 
 * @author lijianqiang
 *
 */
public class SpringWorkClusterExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkClusterExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 6;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delayMillis;
    private final long intervalMillis;
    private final long lockMillis;

    private final String server;
    private final SpringWorkLocker locker;
    private final List<ClusterWork> works;

    private String subject;

    public SpringWorkClusterExecutor(String server, SpringWorkLocker locker) {
        this(server, locker, DELAYS, INTERVALS);
    }

    public SpringWorkClusterExecutor(String server, SpringWorkLocker locker, long delayMillis, long intervalMillis) {
        Objects.requireNonNull(server, "server");
        Objects.requireNonNull(locker, "locker");
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
        LOG.info("server:{}, start", server);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                process();
            }

        }, delayMillis, intervalMillis);
    }

    public SpringWorkClusterExecutor add(ClusterWork work) {
        Objects.requireNonNull(work, "work");

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

        String which = locker.get(subject);
        boolean success = Objects.equals(which, server);
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
            if (SpringString.isEmpty(locker.get(work.subject()))) {
                LOG.warn("server:{} checkIdle subject:{} no worker", server, work.subject());
            }
        }
    }
}
