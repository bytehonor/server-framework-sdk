package com.bytehonor.sdk.server.bytehonor.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.bytehonor.sdk.server.bytehonor.scheduler.plan.JobPlan;

public class JobPlanFactory {

    private static List<JobPlan> POLICY_LIST = new ArrayList<JobPlan>();


    public static void register(JobPlan policy) {
        if (policy != null) {
            POLICY_LIST.add(policy);
        }
    }

    public static Stream<JobPlan> stream() {
        return POLICY_LIST.stream();
    }

    public static Stream<JobPlan> parallelStream() {
        return POLICY_LIST.parallelStream();
    }
}
