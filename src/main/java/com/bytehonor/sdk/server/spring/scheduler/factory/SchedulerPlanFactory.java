package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.plan.SchedulerPlan;

/**
 * @author lijianqiang
 *
 */
public class SchedulerPlanFactory {

    private static final List<SchedulerPlan> LIST = new ArrayList<SchedulerPlan>(1024);

    public static void add(SchedulerPlan plan) {
        if (plan == null) {
            return;
        }
        LIST.add(plan);
    }

    public static List<SchedulerPlan> plans() {
        return LIST;
    }

}
