package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.define.spring.lang.SafeRunner;

public interface SchedulerPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeRunner create(LocalDateTime ldt);
}
