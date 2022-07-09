package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroupPrinter;

/**
 * @author lijianqiang
 *
 */
public abstract class TimeGroupPlan implements TimePlan {

    public abstract TimeGroup group();

    @Override
    public final boolean accept(LocalDateTime ldt) {
        TimeGroup group = group();
        if (group == null) {
            return false;
        }

        return group.match(ldt);
    }

    @Override
    public void print() {
        TimeGroupPrinter.print(group());
    }
}
