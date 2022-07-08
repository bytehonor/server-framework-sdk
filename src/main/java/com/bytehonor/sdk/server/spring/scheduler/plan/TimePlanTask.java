package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.handler.TaskHandler;

public class TimePlanTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanTask.class);

    private final TaskHandler handler;

    private TimePlanTask(TaskHandler handler) {
        this.handler = handler;
    }

    public static TimePlanTask of(TaskHandler handler) {
        Objects.requireNonNull(handler, "handler");

        return new TimePlanTask(handler);
    }

    @Override
    public final void runInSafe() {
        ThreadSleep.rand(1, 19);

        LocalDateTime ldt = LocalDateTime.now();

        if (handler.accept(ldt) == false) {
            return;
        }

        List<TimePlan> plans = TimePlanFactory.listPlanNonPause();
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
