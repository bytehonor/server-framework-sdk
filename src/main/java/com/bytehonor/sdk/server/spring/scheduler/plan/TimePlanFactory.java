package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
