package com.bytehonor.sdk.server.bytehonor.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.bytehonor.scheduler.plan.SchedulerPlan;

public class SchedulerPlanFactory {

    private static List<SchedulerPlan> LIST = new ArrayList<SchedulerPlan>(512);

    public static void put(SchedulerPlan plan) {
        if (plan != null) {
            LIST.add(plan);
        }
    }

    public static List<SchedulerPlan> plans() {
        return LIST;
    }

}
