package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;

public class TimePlanTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanTask.class);

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
        ThreadSleep.rand(1, 19);

        LocalDateTime ldt = LocalDateTime.now();

        if (locker.accept(ldt) == false) {
            return;
        }

        List<TimePlan> plans = TimePlanFactory.listPlanPlay();
        if (plans.isEmpty()) {
            LOG.debug("plans isEmpty");
            return;
        }

        for (TimePlan plan : plans) {
            if (plan.accept(ldt) == false) {
                continue;
            }
            TimePlanExecutor.run(plan, ldt);
        }
    }

}
