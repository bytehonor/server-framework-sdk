package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlan;

/**
 * @author lijianqiang
 *
 */
public class TimePlanFactory {

    private static final List<TimePlan> LIST = new ArrayList<TimePlan>();

    public static void add(TimePlan plan) {
        Objects.requireNonNull(plan, "plan");

        LIST.add(plan);
    }

    public static List<TimePlan> plans() {
        return LIST;
    }

}
