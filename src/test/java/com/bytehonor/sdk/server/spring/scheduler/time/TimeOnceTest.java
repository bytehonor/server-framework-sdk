package com.bytehonor.sdk.server.spring.scheduler.time;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeOnce.TimeOnceBuilder;

public class TimeOnceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeOnceTest.class);

    @Test
    public void test() {
        LocalDateTime ldt = LocalDateTime.of(2022, 7, 5, 12, 30);
        TimeOnceBuilder builder = TimeOnce.builder();
        builder.after(LocalDateTimeUtils.toTimestamp(ldt), TimeConstants.HOUR);
        TimeOnce once = builder.build();

        LOG.info("test cron:{}", once.getCron());
        boolean isOk = once.match(ldt.plusHours(1L));
        assertTrue("*test*", isOk);
    }

    @Test
    public void test2() {
        LocalDateTime ldt = LocalDateTime.of(2022, 7, 5, 15, 30);
        TimeOnceBuilder builder = TimeOnce.builder();
        builder.at(5, 15, 30);
        TimeOnce once = builder.build();

        LOG.info("test2 cron:{}", once.getCron());
        boolean isOk = once.match(ldt);
        assertTrue("*test2*", isOk);
    }
}
