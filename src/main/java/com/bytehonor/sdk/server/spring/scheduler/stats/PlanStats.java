package com.bytehonor.sdk.server.spring.scheduler.stats;

import java.util.Objects;

public class PlanStats {

    private String name;

    private Long time;

    private Integer total;

    public PlanStats() {
        this.time = System.currentTimeMillis();
        this.total = 0;
    }

    public static PlanStats of(String name) {
        Objects.requireNonNull(name, "name");

        PlanStats model = new PlanStats();
        model.setName(name);
        return model;
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

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
