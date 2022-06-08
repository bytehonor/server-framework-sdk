package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.define.bytehonor.task.SafeTask;

public interface SchedulerPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeTask createTask(LocalDateTime ldt);
}
