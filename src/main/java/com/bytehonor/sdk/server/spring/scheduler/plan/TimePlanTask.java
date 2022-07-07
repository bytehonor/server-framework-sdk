package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;

public class TimePlanTask extends SafeTask {

    private final TaskLocker locker;

    private TimePlanTask(TaskLocker locker) {
        this.locker = locker;
    }

    public static TimePlanTask of(TaskLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new TimePlanTask(locker);
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
