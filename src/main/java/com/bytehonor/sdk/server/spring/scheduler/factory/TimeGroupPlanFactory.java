package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimeGroupPlan;

/**
 * @author lijianqiang
 *
 */
public class TimeGroupPlanFactory {

    private static final List<TimeGroupPlan> LIST = new ArrayList<TimeGroupPlan>(1024);

    public static void put(TimeGroupPlan plan) {
        if (plan == null) {
            return;
        }
        LIST.add(plan);
    }

    public static List<TimeGroupPlan> plans() {
        return LIST;
    }

}
