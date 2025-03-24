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
public class SpringWorkClusterScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkClusterScheduler.class);

    private static final long DELAYS = TimeConstants.SECOND * 6;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delayMillis;
    private final long intervalMillis;
    private final long lockMillis;

    private final String name;
    private final SpringWorkLocker locker;
    private final List<SpringWork> works;

    private String subject;

    public SpringWorkClusterScheduler(String name, SpringWorkLocker locker) {
        this(name, locker, DELAYS, INTERVALS);
    }

    public SpringWorkClusterScheduler(String name, SpringWorkLocker locker, long delayMillis, long intervalMillis) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(locker, "locker");
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
        this.lockMillis = intervalMillis * 2;
        this.name = name;
        this.locker = locker;
        this.works = new ArrayList<SpringWork>();
        this.subject = "";
    }

    public void start() {
        if (CollectionUtils.isEmpty(works)) {
            LOG.warn("works empty");
            return;
        }
        LOG.info("name:{}, start", name);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                doWork();
            }

        }, delayMillis, intervalMillis);
    }

    public SpringWorkClusterScheduler add(SpringWork work) {
        Objects.requireNonNull(work, "work");

        LOG.info("subject:{}", work.subject());

        if (SpringString.isEmpty(work.subject()) == false) {
            works.add(work);
        }

        return this;
    }

    private void doWork() {
        try {
            competeSubject();
            keepAlive();
            checkIdle();
        } catch (Exception e) {
            LOG.error("run error", e);
        }

    }

    private void competeSubject() {
        if (SpringString.isEmpty(subject) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(works)) {
            LOG.warn("works empty");
            return;
        }

        for (SpringWork work : works) {
            if (locker.lock(work.subject(), name, lockMillis) == false) {
                continue;
            }

            subject = work.subject();
            work.start();
            LOG.info("competeSubject done, subject:{}, name:{}", subject, name);
            break;
        }
    }

    private void keepAlive() {
        if (SpringString.isEmpty(subject)) {
            LOG.debug("keepAlive end, subject empty, name:{}", name);
            return;
        }

        String which = locker.get(subject);
        boolean success = Objects.equals(which, name);
        LOG.info("keepAlive success:{}, subject:{}, name:{}", success, subject, name);
        if (success) {
            locker.expireAt(subject, System.currentTimeMillis() + lockMillis);
        }
    }

    private void checkIdle() {
        if (CollectionUtils.isEmpty(works)) {
            return;
        }

        for (SpringWork work : works) {
            if (SpringString.isEmpty(locker.get(work.subject()))) {
                LOG.warn("checkIdle subject:{} no worker", work.subject());
            }
        }
    }
}
