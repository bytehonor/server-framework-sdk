package com.bytehonor.sdk.server.spring.scheduler.handler;

import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.stats.PlanStats;

public interface TaskRecord {

    public void save(PlanStats planStats);

    public List<PlanStats> list(int size);
}
