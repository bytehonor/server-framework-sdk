package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.server.spring.scheduler.task.PrintTimeTask;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup.TimeGroupBuilder;

public class PrintTimePlan extends TimeGroupPlan {

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
