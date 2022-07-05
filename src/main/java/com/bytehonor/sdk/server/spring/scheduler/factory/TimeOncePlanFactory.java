package com.bytehonor.sdk.server.spring.scheduler.factory;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimeOncePlan;

/**
 * @author lijianqiang
 *
 */
public class TimeOncePlanFactory {

    private static final List<TimeOncePlan> LIST = new ArrayList<TimeOncePlan>(1024);

    public static void put(TimeOncePlan plan) {
        if (plan == null) {
            return;
        }
        LIST.add(plan);
    }

    public static List<TimeOncePlan> plans() {
        return LIST;
    }

}
