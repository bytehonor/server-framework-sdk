package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.server.spring.scheduler.time.TimeOnce;

/**
 * @author lijianqiang
 *
 */
public abstract class TimeOncePlan implements SchedulerPlan {

    public abstract TimeOnce once();

    @Override
    public final boolean accept(LocalDateTime ldt) {
        TimeOnce once = once();
        if (once == null) {
            return false;
        }

        return once.match(ldt);
    }

}
