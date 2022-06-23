package com.bytehonor.sdk.server.spring.scheduler.time;

import java.util.ArrayList;
import java.util.List;

public class TimeCronBuilder {

    private Integer[] minutes;

    private Integer[] hours;

    private Integer[] days;

    private TimeCronBuilder() {
    }

    public static TimeCronBuilder make() {
        return new TimeCronBuilder();
    }

    public TimeCronBuilder mintueAt(Integer... minutes) {
        this.minutes = minutes;
        return this;
    }

    public TimeCronBuilder hourAt(Integer... hours) {
        this.hours = hours;
        return this;
    }

    public TimeCronBuilder dayAt(Integer... days) {
        this.days = days;
        return this;
    }

    public List<AccurateTimeCron> build() {
        if (minutes == null || minutes.length < 1) {
            minutes = new Integer[] { AccurateTimeCron.ANY };
        }
        if (hours == null || hours.length < 1) {
            hours = new Integer[] { AccurateTimeCron.ANY };
        }
        if (days == null || days.length < 1) {
            days = new Integer[] { AccurateTimeCron.ANY };
        }
        int mSize = minutes.length;
        int hSize = hours.length;
        int dSize = days.length;
        int size = mSize * hSize * dSize;

        List<AccurateTimeCron> result = new ArrayList<AccurateTimeCron>(size * 2);

        for (int m : minutes) {
            for (int h : hours) {
                for (int d : days) {
                    result.add(new AccurateTimeCron(m, h, d));
                }
            }
        }

        return result;
    }
}
