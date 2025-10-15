package com.bytehonor.sdk.framework.server.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.Sleep;
import com.bytehonor.sdk.framework.server.scheduler.plan.PrintTimePlan;

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
