package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;

public class TimeOnce implements TimeCron {

    private final DefineTimeCron cron;

    public TimeOnce(DefineTimeCron cron) {
        this.cron = cron;
    }

    @Override
    public boolean match(LocalDateTime ldt) {
        if (cron == null) {
            return false;
        }
        return cron.match(ldt);
    }

    public DefineTimeCron getCron() {
        return cron;
    }

    public static TimeOnceBuilder builder() {
        return new TimeOnceBuilder();
    }

    public static class TimeOnceBuilder {

        private LocalDateTime ldt;

        private TimeOnceBuilder() {
            ldt = LocalDateTime.now();
        }

        public TimeOnceBuilder after(long millis) {
            return after(System.currentTimeMillis(), millis);
        }

        public TimeOnceBuilder after(long now, long millis) {
            long ts = now + millis;
            ldt = LocalDateTimeUtils.fromTimestamp(ts);
            return this;
        }

        public TimeOnceBuilder at(int day, int hour, int minute) {
            ldt = LocalDateTime.of(ldt.getYear(), ldt.getMonthValue(), day, hour, minute);
            return this;
        }

        public TimeOnce build() {
            DefineTimeCron cron = new DefineTimeCron();
            cron.setMinute(ldt.getMinute());
            cron.setHour(ldt.getHour());
            cron.setDay(ldt.getDayOfMonth());
            return new TimeOnce(cron);
        }
    }
}
