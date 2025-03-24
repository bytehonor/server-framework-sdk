package com.bytehonor.sdk.server.spring.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataList;
import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanScheduler;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanStatus;
import com.bytehonor.sdk.server.spring.web.context.ServerContext;

/**
 * 注入controller
 * 
 * @author lijianqiang
 *
 */
@RestController
@RequestMapping("/actuator/scheduler")
public class SchedulerController {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerController.class);

    @GetMapping("/plans")
    public DataList<SpringPlanStatus> plans() {
        LOG.info("plans");
        List<SpringPlanStatus> list = SpringPlanScheduler.plans();
        return DataList.of(list);
    }

    @GetMapping("/detail/{name}")
    public SpringPlanStatus detail(@PathVariable("name") String name) {
        LOG.info("detail name:{}", name);
        return SpringPlanScheduler.detail(name);
    }

    @GetMapping("/pause/{name}")
    public DataString pause(@PathVariable("name") String name) {
        LOG.info("pause name:{}", name);
        SpringPlanScheduler.pause(name);
        return DataString.of(ServerContext.self().getId());
    }

    @GetMapping("/play/{name}")
    public DataString play(@PathVariable("name") String name) {
        LOG.info("play name:{}", name);
        SpringPlanScheduler.play(name);
        return DataString.of(ServerContext.self().getId());
    }

    @GetMapping("/run/{name}")
    public DataString run(@PathVariable("name") String name) {
        LOG.info("run name:{}", name);
        SpringPlanScheduler.run(name);
        return DataString.of(ServerContext.self().getId());
    }

    @GetMapping("/print/{name}")
    public DataString print(@PathVariable("name") String name) {
        LOG.info("print name:{}", name);
        SpringPlanScheduler.print(name);
        return DataString.of(ServerContext.self().getId());
    }
}
