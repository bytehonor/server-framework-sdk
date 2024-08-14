package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

/**
 * @author lijianqiang
 *
 */
public abstract class AbstractTimePlan implements TimePlan {

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
