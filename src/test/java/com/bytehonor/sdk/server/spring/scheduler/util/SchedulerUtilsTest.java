package com.bytehonor.sdk.server.spring.scheduler.util;

import java.time.LocalTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;

public class SchedulerUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerUtilsTest.class);

    @Test
    public void test() {
        for (int i = 0; i < 60; i++) {
            LOG.info("secondNow:{}", LocalTime.now().getSecond());
            long delayMillis = SchedulerUtils.delayMillis(i);
            ThreadSleep.sleep(delayMillis);
            LOG.info("i:{}, {}, {}", i, SchedulerUtils.delaySeconds(i), delayMillis);
        }
    }

}
