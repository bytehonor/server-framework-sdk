package com.bytehonor.sdk.server.spring.scheduler.plan;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import com.bytehonor.sdk.server.spring.scheduler.constant.SchedulerConstants;
import com.bytehonor.sdk.server.spring.scheduler.plan.time.DefineTimeCron;

public class DefineTimeCronTest {

    @Test
    public void testMatch() {
        int minute = 2;
        int hour = 3;
        DefineTimeCron cron = new DefineTimeCron(minute, hour, SchedulerConstants.ANY);
        LocalTime lt = LocalTime.of(hour, minute, 0);
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), lt);

        boolean isOk = cron.match(ldt);
        assertTrue("*testMatch*", isOk);
    }

}
