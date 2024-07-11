package com.bytehonor.sdk.server.spring.work;

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
public class SubjectWorkOperator extends LoopIntervalTask {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectWorkOperator.class);

    private static final long TASK_DELAY_MILLIS = TimeConstants.SECOND * 3;

    private static final long TASK_INTERVAL_MILLIS = TimeConstants.SECOND * 60;

    private final long delayMillis;
    private final long intervalMillis;
    private final long lockMillis;

    private final String name;
    private final SubjectLocker locker;
    private final List<SubjectWork> works;
    private final Thread thread;

    private String subject;

    public SubjectWorkOperator(String name, SubjectLocker locker) {
        this(name, TASK_DELAY_MILLIS, TASK_INTERVAL_MILLIS, locker);
    }

    public SubjectWorkOperator(String name, long delayMillis, long intervalMillis, SubjectLocker locker) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(locker, "locker");
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
        this.lockMillis = intervalMillis * 2;
        this.name = name;
        this.locker = locker;
        this.works = new ArrayList<SubjectWork>();
        this.subject = "";
        this.thread = new Thread(this);
        this.thread.setName(SubjectWorkOperator.class.getSimpleName());
    }

    public void start() {
        thread.start();
    }

    public SubjectWorkOperator plan(SubjectWork work) {
        Objects.requireNonNull(work, "work");
        Objects.requireNonNull(work.subject(), "subject");

        works.add(work);
        return this;
    }

    private void doCompete() {
        if (SpringString.isEmpty(subject) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(works)) {
            LOG.info("works empty");
            return;
        }

        for (SubjectWork work : works) {
            if (locker.lock(work.subject(), name, lockMillis) == false) {
                continue;
            }

            subject = work.subject();
            work.start();
            LOG.info("doCompete subject:{}, name:{}", subject, name);
            break;
        }
    }

    private void doKeep() {
        if (SpringString.isEmpty(subject)) {
            LOG.info("doKeep subject null, name:{}", name);
            return;
        }

        String which = locker.get(subject);
        boolean success = Objects.equals(which, name);
        LOG.info("doKeep success:{}, subject:{}, name:{}", success, subject, name);
        if (success) {
            locker.expireAt(subject, System.currentTimeMillis() + lockMillis);
        }
    }

    private void doCheck() {
        if (CollectionUtils.isEmpty(works)) {
            return;
        }

        for (SubjectWork plan : works) {
            if (SpringString.isEmpty(locker.get(plan.subject()))) {
                LOG.warn("doCheck subject:{} no worker", plan.subject());
            }
        }
    }

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
        try {
            doCompete();
            doKeep();
            doCheck();
        } catch (Exception e) {
            LOG.error("run error", e);
        }

    }
}
