package com.bytehonor.sdk.server.spring.scheduler.plan;

public class TimePlanStatus {

    private final String name;

    private final Boolean paused;

    public TimePlanStatus(String name, Boolean paused) {
        this.name = name;
        this.paused = paused;
    }

    public String getName() {
        return name;
    }

    public Boolean getPaused() {
        return paused;
    }

}
