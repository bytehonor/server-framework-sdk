package com.bytehonor.sdk.server.spring.scheduler.time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijianqiang
 *
 */
public class TimeCronBuilder {

    private int[] minutes;

    private int[] hours;

    private int[] days;

    private TimeCronBuilder() {
    }

    public static TimeCronBuilder make() {
        return new TimeCronBuilder();
    }

    public TimeCronBuilder mintueAt(int... minutes) {
        this.minutes = minutes;
        return this;
    }

    public TimeCronBuilder hourAt(int... hours) {
        this.hours = hours;
        return this;
    }

    public TimeCronBuilder dayAt(int... days) {
        this.days = days;
        return this;
    }

    public List<PeriodTimeCron> build() {
        if (minutes == null || minutes.length < 1) {
            minutes = new int[] { PeriodTimeCron.ANY };
        }
        if (hours == null || hours.length < 1) {
            hours = new int[] { PeriodTimeCron.ANY };
        }
        if (days == null || days.length < 1) {
            days = new int[] { PeriodTimeCron.ANY };
        }
        int mSize = minutes.length;
        int hSize = hours.length;
        int dSize = days.length;
        int size = mSize * hSize * dSize;

        List<PeriodTimeCron> result = new ArrayList<PeriodTimeCron>(size * 2);

        for (int m : minutes) {
            for (int h : hours) {
                for (int d : days) {
                    result.add(new PeriodTimeCron(m, h, d));
                }
            }
        }

        return result;
    }
}
