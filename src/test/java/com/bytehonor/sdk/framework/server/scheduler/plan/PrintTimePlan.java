package com.bytehonor.sdk.framework.server.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.server.scheduler.plan.time.TimeGroup;
import com.bytehonor.sdk.framework.server.scheduler.plan.time.TimeGroup.TimeGroupBuilder;
import com.bytehonor.sdk.framework.server.scheduler.task.PrintTimeTask;

public class PrintTimePlan extends AbstractSpringPlan {

    private TimeGroup group;

    public PrintTimePlan() {
        TimeGroupBuilder builder = TimeGroup.builder();
        group = builder.every().build();
    }

    @Override
    public TimeGroup group() {
        return group;
    }

    @Override
    public SafeTask create(LocalDateTime ldt) {
        return new PrintTimeTask(ldt);
    }

}
