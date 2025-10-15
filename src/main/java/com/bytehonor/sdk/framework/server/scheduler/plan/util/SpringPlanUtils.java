package com.bytehonor.sdk.framework.server.scheduler.plan.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.util.TimeFormatUtils;

public class SpringPlanUtils {

    public static long delaySeconds(int secondAt) {
        int secondNow = LocalTime.now().getSecond();
        int delaySeconds = secondAt - secondNow;
        if (delaySeconds < 0) {
            delaySeconds += 60;
        }
        return delaySeconds;
    }

    public static long delaySeconds2(int secondAt) {
        return delayMillis(secondAt) / 1000;
    }

    public static long delayMillis(int secondAt) {
        long now = System.currentTimeMillis();
        LocalDateTime later = TimeFormatUtils.fromTimestamp(now + TimeConstants.MINUTE);
        LocalDateTime at = LocalDateTime.of(later.toLocalDate(),
                LocalTime.of(later.getHour(), later.getMinute(), secondAt));
        long delays = TimeFormatUtils.toTimestamp(at) - now;
        if (delays > TimeConstants.MINUTE) {
            delays = delays - TimeConstants.MINUTE;
        }
        return delays;
    }
}
