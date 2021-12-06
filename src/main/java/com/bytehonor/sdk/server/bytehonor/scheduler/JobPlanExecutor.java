package com.bytehonor.sdk.server.bytehonor.scheduler;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.task.SafeTask;
import com.bytehonor.sdk.server.bytehonor.pool.AsyncTaskPoolExecutor;

public class JobPlanExecutor {

    public static void start(final LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");
        // 并发执行
        JobPlanFactory.parallelStream().forEach(policy -> {
            if (policy.accept(ldt)) {
                SafeTask task = policy.createTask(ldt);
                if (task != null) {
                    doRun(task);
                }
            }
        });
    }

    public static void doRun(SafeTask task) {
        Objects.requireNonNull(task, "task");
        AsyncTaskPoolExecutor.execute(task);
    }

}
