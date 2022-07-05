package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.SchedulerPlanExecutor;
import com.bytehonor.sdk.server.spring.scheduler.lock.TimeLocker;

public class PeriodTask extends SafeRunner {

    private final TimeLocker locker;

    private PeriodTask(TimeLocker locker) {
        this.locker = locker;
    }

    public static PeriodTask create(TimeLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new PeriodTask(locker);
    }

    @Override
    public final void runInSafe() {
        ThreadSleep.rand(1, 5);

        LocalDateTime ldt = LocalDateTime.now();
        if (locker.accept(ldt) == false) {
            return;
        }

        SchedulerPlanExecutor.run(ldt);
    }

}
