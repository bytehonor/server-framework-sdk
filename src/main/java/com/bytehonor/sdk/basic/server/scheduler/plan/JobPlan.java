package com.bytehonor.sdk.basic.server.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.basic.server.thread.SafeTask;

public interface JobPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeTask createTask(LocalDateTime ldt);
}
