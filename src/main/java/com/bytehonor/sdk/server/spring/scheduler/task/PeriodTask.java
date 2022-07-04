package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.SchedulerPlanExecutor;
import com.bytehonor.sdk.server.spring.scheduler.lock.PeriodLocker;

public class PeriodTask extends SafeRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodTask.class);

    private final PeriodLocker locker;

    public PeriodTask(PeriodLocker locker) {
        this.locker = locker;
    }

    @Override
    public final void runInSafe() {
        if (locker == null) {
            LOG.error("no locker");
            return;
        }

        ThreadSleep.rand(1, 5);

        LocalDateTime ldt = LocalDateTime.now();
        if (locker.accept(ldt) == false) {
            return;
        }

        SchedulerPlanExecutor.run(ldt);
    }

}
