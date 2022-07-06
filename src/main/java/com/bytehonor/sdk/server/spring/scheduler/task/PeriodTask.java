package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanExecutor;

public class PeriodTask extends SafeTask {

    private final TaskLocker locker;

    private PeriodTask(TaskLocker locker) {
        this.locker = locker;
    }

    public static PeriodTask create(TaskLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new PeriodTask(locker);
    }

    @Override
    public final void runInSafe() {
        ThreadSleep.rand(1, 15);

        LocalDateTime ldt = LocalDateTime.now();
        if (locker.accept(ldt) == false) {
            return;
        }

        TimePlanExecutor.run(ldt);
    }

}
