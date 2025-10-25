package com.bytehonor.sdk.framework.server.scheduler.plan.time;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import com.bytehonor.sdk.framework.server.scheduler.constant.SchedulerConstants;

public class TimeCronTest {

    @Test
    public void testMatch() {
        int minute = 2;
        int hour = 3;
        TimeCron cron = new TimeCron(minute, hour, SchedulerConstants.ANY, SchedulerConstants.ANY);
        LocalTime lt = LocalTime.of(hour, minute, 0);
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), lt);

        boolean isOk = cron.match(ldt);
        assertTrue("*testMatch*", isOk);
    }

}
