package com.bytehonor.sdk.server.spring.scheduler.stats;

import java.util.Objects;

public class PlanStats {

    private String key;

    private String name;

    private final Long time;

    public PlanStats() {
        this.time = System.currentTimeMillis();
    }

    public static PlanStats of(String name) {
        Objects.requireNonNull(name, "name");

        PlanStats model = new PlanStats();
        model.setName(name);
        model.setKey(new StringBuilder().append(model.getName()).append(":").append(model.getTime()).toString());
        return model;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

}
