package com.bytehonor.sdk.server.spring.scheduler.plan;

public class TimePlanHolder {

    private final String name;

    private final TimePlan plan;

    public TimePlanHolder(TimePlan plan) {
        this.name = plan.getClass().getSimpleName();
        this.plan = plan;
    }

    public String getName() {
        return name;
    }

    public TimePlan getPlan() {
        return plan;
    }
}
