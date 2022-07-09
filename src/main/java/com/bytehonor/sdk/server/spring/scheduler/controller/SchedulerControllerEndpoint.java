package com.bytehonor.sdk.server.spring.scheduler.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.result.DataListVO;
import com.bytehonor.sdk.define.spring.result.StringResultVO;
import com.bytehonor.sdk.server.spring.config.ServerConfig;
import com.bytehonor.sdk.server.spring.constant.SpringServerConstants;
import com.bytehonor.sdk.server.spring.scheduler.SpringScheduler;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanStats;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanStatus;

@ControllerEndpoint(id = SpringServerConstants.SCHEDULER_ENDPOINT)
public class SchedulerControllerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerControllerEndpoint.class);

    @ResponseBody
    @GetMapping("plans")
    public DataListVO<TimePlanStatus> plans() {
        LOG.info("plans");
        List<TimePlanStatus> list = SpringScheduler.plans();
        return DataListVO.of(list);
    }
    
    @ResponseBody
    @GetMapping("stats")
    public DataListVO<TimePlanStats> stats() {
        LOG.info("stats");
        List<TimePlanStats> list = SpringScheduler.stats();
        return DataListVO.of(list);
    }

    @ResponseBody
    @GetMapping("pause/{name}")
    public StringResultVO pause(@PathVariable("name") String name) {
        LOG.info("pause name:{}", name);
        SpringScheduler.pause(name);
        return StringResultVO.of(ServerConfig.self().getId());
    }

    @ResponseBody
    @GetMapping("play/{name}")
    public StringResultVO play(@PathVariable("name") String name) {
        LOG.info("play name:{}", name);
        SpringScheduler.play(name);
        return StringResultVO.of(ServerConfig.self().getId());
    }

    @ResponseBody
    @GetMapping("run/{name}")
    public StringResultVO run(@PathVariable("name") String name) {
        LOG.info("run name:{}", name);
        SpringScheduler.run(name);
        return StringResultVO.of(ServerConfig.self().getId());
    }
}
