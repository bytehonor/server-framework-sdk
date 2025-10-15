package com.bytehonor.sdk.framework.server.scheduler.plan.time;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.framework.server.scheduler.constant.SchedulerConstants;

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

    public List<DefineTimeCron> build() {
        if (minutes == null || minutes.length < 1) {
            minutes = new int[] { SchedulerConstants.ANY };
        }
        if (hours == null || hours.length < 1) {
            hours = new int[] { SchedulerConstants.ANY };
        }
        if (days == null || days.length < 1) {
            days = new int[] { SchedulerConstants.ANY };
        }
        int mSize = minutes.length;
        int hSize = hours.length;
        int dSize = days.length;
        int size = mSize * hSize * dSize;

        List<DefineTimeCron> result = new ArrayList<DefineTimeCron>(size * 2);

        for (int m : minutes) {
            for (int h : hours) {
                for (int d : days) {
                    result.add(new DefineTimeCron(m, h, d));
                }
            }
        }

        return result;
    }
}
