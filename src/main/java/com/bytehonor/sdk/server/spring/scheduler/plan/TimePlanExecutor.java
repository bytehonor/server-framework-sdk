package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public class TimePlanExecutor {

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2);

    public static void run(TimePlan plan, LocalDateTime ldt) {
        Objects.requireNonNull(plan, "plan");
        Objects.requireNonNull(ldt, "ldt");

        add(plan.create(ldt));
    }

    private static void add(SafeTask task) {
        if (task == null) {
            return;
        }
        SERVICE.execute(task);
    }

}
