package com.bytehonor.sdk.server.spring.scheduler.util;

public class SchedulerUtils {

    public static long delaySeconds(int secondAt, int secondNow) {
        int delaySeconds = secondAt - secondNow;
        if (delaySeconds < 0) {
            delaySeconds += 60;
        }
        return delaySeconds;
    }
}
