package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlan;

/**
 * @author lijianqiang
 *
 */
public class TimePlanFactory {

    private static final List<TimePlan> LIST = new ArrayList<TimePlan>();

    public static void add(TimePlan plan) {
        if (plan == null) {
            return;
        }
        LIST.add(plan);
    }

    public static List<TimePlan> plans() {
        return LIST;
    }

}
