package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.time.PrintTimePlan;

public class SpringSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringSchedulerTest.class);

    @Test
    public void testStart() {
        SpringScheduler.start();

        SpringScheduler.add(new PrintTimePlan());

        Sleep.millis(TimeConstants.MINUTE * 1);

        String name = PrintTimePlan.class.getSimpleName();

        LOG.info("pause");
        SpringScheduler.pause(name);

        Sleep.millis(TimeConstants.MINUTE * 1);

        LOG.info("run");
        SpringScheduler.run(name);

        Sleep.millis(TimeConstants.MINUTE * 5);

        LOG.info("play");
        SpringScheduler.play(name);

        Sleep.millis(TimeConstants.MINUTE * 10);
    }

}
