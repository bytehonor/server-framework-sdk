package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;
import com.bytehonor.sdk.server.spring.scheduler.stats.PlanStatsHandler;

/**
 * @author lijianqiang
 *
 */
public class TimePlanExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanExecutor.class);

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2);

    public static void run(final LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");

        List<TimePlan> plans = TimePlanFactory.plans();
        if (plans.isEmpty()) {
            LOG.warn("plans isEmpty");
            return;
        }

        for (TimePlan plan : plans) {
            String name = plan.getClass().getSimpleName();
            if (PlanPauseCacheHolder.isPause(name)) {
                continue;
            }

            if (plan.accept(ldt) == false) {
                continue;
            }

            put(plan.create(ldt));

            PlanStatsHandler.increase(name);
        }
    }

    private static void put(SafeTask runner) {
        if (runner == null) {
            return;
        }
        SERVICE.execute(runner);
    }

}
