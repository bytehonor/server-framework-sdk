package com.bytehonor.sdk.server.spring.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;
import com.bytehonor.sdk.server.spring.scheduler.plan.SchedulerPlan;

public class SchedulerPlanExecutor {

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(1);

    public static void run(final LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");

        List<SchedulerPlan> plans = SchedulerPlanFactory.plans();
        for (SchedulerPlan plan : plans) {
            if (plan.accept(ldt)) {
                doRun(plan.create(ldt));
            }
        }
    }

    public static void doRun(SafeRunner runner) {
        if (runner == null) {
            return;
        }
        SERVICE.execute(runner);
    }

}
