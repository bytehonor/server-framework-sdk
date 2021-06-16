package com.bytehonor.sdk.server.bytehonor.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.define.bytehonor.task.SafeTask;

public interface JobPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeTask createTask(LocalDateTime ldt);
}
