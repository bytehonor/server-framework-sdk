package com.bytehonor.sdk.server.spring.scheduler.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.response.DataList;
import com.bytehonor.sdk.define.spring.response.DataString;
import com.bytehonor.sdk.server.spring.constant.ServerEndpointConstants;
import com.bytehonor.sdk.server.spring.context.ServerContext;
import com.bytehonor.sdk.server.spring.scheduler.SpringScheduler;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanStatus;

/**
 * 注入controller, {@link Constants @ServerEndpointConstants}
 * 
 * @author lijianqiang
 *
 */
@ControllerEndpoint(id = ServerEndpointConstants.SCHEDULER_ENDPOINT)
public class SchedulerControllerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerControllerEndpoint.class);

    @ResponseBody
    @GetMapping("plans")
    public DataList<TimePlanStatus> plans() {
        LOG.info("plans");
        List<TimePlanStatus> list = SpringScheduler.plans();
        return DataList.of(list);
    }

    @ResponseBody
    @GetMapping("detail/{name}")
    public TimePlanStatus detail(@PathVariable("name") String name) {
        LOG.info("detail name:{}", name);
        return SpringScheduler.detail(name);
    }

    @ResponseBody
    @GetMapping("pause/{name}")
    public DataString pause(@PathVariable("name") String name) {
        LOG.info("pause name:{}", name);
        SpringScheduler.pause(name);
        return DataString.of(ServerContext.me().getId());
    }

    @ResponseBody
    @GetMapping("play/{name}")
    public DataString play(@PathVariable("name") String name) {
        LOG.info("play name:{}", name);
        SpringScheduler.play(name);
        return DataString.of(ServerContext.me().getId());
    }

    @ResponseBody
    @GetMapping("run/{name}")
    public DataString run(@PathVariable("name") String name) {
        LOG.info("run name:{}", name);
        SpringScheduler.run(name);
        return DataString.of(ServerContext.me().getId());
    }

    @ResponseBody
    @GetMapping("print/{name}")
    public DataString print(@PathVariable("name") String name) {
        LOG.info("print name:{}", name);
        SpringScheduler.print(name);
        return DataString.of(ServerContext.me().getId());
    }
}
