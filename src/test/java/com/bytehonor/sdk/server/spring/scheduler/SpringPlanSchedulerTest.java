package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.plan.PrintTimePlan;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanScheduler;

public class SpringPlanSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanSchedulerTest.class);

    @Test
    public void testStart() {
        SpringPlanScheduler.start();

        SpringPlanScheduler.add(new PrintTimePlan());

        Sleep.millis(TimeConstants.MINUTE * 1);

        String name = PrintTimePlan.class.getSimpleName();

        LOG.info("pause");
        SpringPlanScheduler.pause(name);

        Sleep.millis(TimeConstants.MINUTE * 1);

        LOG.info("run");
        SpringPlanScheduler.run(name);

        Sleep.millis(TimeConstants.MINUTE * 5);

        LOG.info("play");
        SpringPlanScheduler.play(name);

        Sleep.millis(TimeConstants.MINUTE * 10);
    }

}
