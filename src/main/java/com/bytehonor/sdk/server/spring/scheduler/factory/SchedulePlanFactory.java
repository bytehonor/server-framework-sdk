package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.plan.SchedulePlan;

/**
 * @author lijianqiang
 *
 */
public class SchedulePlanFactory {

    private static final List<SchedulePlan> LIST = new ArrayList<SchedulePlan>(1024);

    public static void add(SchedulePlan plan) {
        if (plan == null) {
            return;
        }
        LIST.add(plan);
    }

    public static List<SchedulePlan> plans() {
        return LIST;
    }

}
