package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.lang.spring.thread.SafeRunner;

/**
 * @author lijianqiang
 *
 */
public interface SchedulerPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeRunner create(LocalDateTime ldt);
}
