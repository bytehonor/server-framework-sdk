package com.bytehonor.sdk.framework.server.scheduler.util;

import java.time.LocalTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.thread.Sleep;
import com.bytehonor.sdk.framework.server.scheduler.plan.util.SpringPlanUtils;

public class SchedulerUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerUtilsTest.class);

    @Test
    public void test() {
        for (int i = 0; i < 60; i++) {
            LOG.info("secondNow:{}", LocalTime.now().getSecond());
            long delayMillis = SpringPlanUtils.delayMillis(i);
            Sleep.millis(delayMillis);
            LOG.info("i:{}, {}, {}", i, SpringPlanUtils.delaySeconds(i), delayMillis);
        }
    }

}
