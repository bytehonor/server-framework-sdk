package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public class TimePlanExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanExecutor.class);

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2);

    public static void run(TimePlan plan, LocalDateTime ldt) {
        Objects.requireNonNull(plan, "plan");
        Objects.requireNonNull(ldt, "ldt");

        LOG.info("name:{} run", plan.getClass().getSimpleName());
        add(plan.create(ldt));
    }

    private static void add(SafeTask task) {
        if (task == null) {
            return;
        }
        SERVICE.execute(task);
    }

}
