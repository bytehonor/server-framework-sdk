package com.bytehonor.sdk.server.spring.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.result.IntegerResultVO;
import com.bytehonor.sdk.define.spring.result.StringResultVO;
import com.bytehonor.sdk.server.spring.config.SpringBootStandardConfiguration;
import com.bytehonor.sdk.server.spring.constant.SpringServerConstants;
import com.bytehonor.sdk.server.spring.scheduler.stats.PlanStatsHandler;

@ControllerEndpoint(id = SpringServerConstants.SCHEDULER_ENDPOINT)
public class SchedulerControllerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootStandardConfiguration.class);

    @ResponseBody
    @GetMapping("print")
    public IntegerResultVO schedulerPrint() {
        LOG.info("schedulerPrint");
        PlanStatsHandler.print();
        return IntegerResultVO.of(PlanStatsHandler.size());
    }

    @ResponseBody
    @GetMapping("stop/{name}")
    public StringResultVO schedulerStop(@PathVariable("name") String name) {
        LOG.info("schedulerStop name:{}", name);
        return StringResultVO.ok();
    }

    @ResponseBody
    @GetMapping("start/{name}")
    public StringResultVO schedulerStart(@PathVariable("name") String name) {
        LOG.info("schedulerStart name:{}", name);
        return StringResultVO.ok();
    }
}
