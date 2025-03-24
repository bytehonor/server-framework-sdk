package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.LoopIntervalTask;

/**
 * 竞争任务线程
 * 
 * @author lijianqiang
 *
 */
public class SubjectWorkOperator {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectWorkOperator.class);

    private final long lockMillis;

    private final String name;
    private final SubjectLocker locker;
    private final List<SubjectWork> works;
    private final Thread thread;

    private String subject;

    public SubjectWorkOperator(String name, SubjectLocker locker) {
        this(name, TimeConstants.SECOND, TimeConstants.MINUTE, locker);
    }

    public SubjectWorkOperator(String name, final long delayMillis, final long intervalMillis, SubjectLocker locker) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(locker, "locker");
        this.lockMillis = intervalMillis * 2;
        this.name = name;
        this.locker = locker;
        this.works = new ArrayList<SubjectWork>();
        this.subject = "";
        this.thread = new Thread(new LoopIntervalTask() {

            @Override
            public long delays() {
                return delayMillis;
            }

            @Override
            public long intervals() {
                return intervalMillis;
            }

            @Override
            public void runThenSleep() {
                doWork();
            }

        });
        this.thread.setName(SubjectWorkOperator.class.getSimpleName());
    }

    public void start() {
        if (CollectionUtils.isEmpty(works)) {
            LOG.warn("works empty");
            return;
        }
        LOG.info("name:{}, start", name);
        thread.start();
    }

    public SubjectWorkOperator add(SubjectWork work) {
        Objects.requireNonNull(work, "work");

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

        for (SubjectWork work : works) {
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

        for (SubjectWork work : works) {
            if (SpringString.isEmpty(locker.get(work.subject()))) {
                LOG.warn("checkIdle subject:{} no worker", work.subject());
            }
        }
    }
}
