package com.bytehonor.sdk.server.spring.scheduler.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.result.IntegerResultVO;
import com.bytehonor.sdk.server.spring.scheduler.stats.PlanStatsHandler;

@ControllerEndpoint(id = "scheduler", enableByDefault = true)
public class SchedulerControllerEndpoint {

    @ResponseBody
    @GetMapping("/scheduler/print")
    public IntegerResultVO schedulerPrint() {
        PlanStatsHandler.print();
        return IntegerResultVO.of(PlanStatsHandler.size());
    }
}
