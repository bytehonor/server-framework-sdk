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

    private int[] weeks;

    private TimeCronBuilder() {
    }

    public static TimeCronBuilder make() {
        return new TimeCronBuilder();
    }

    /**
     * 分钟匹配配置。
     * 
     * @param minutes 分钟值
     * @return 构建器
     */
    public TimeCronBuilder minuteAt(int... minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * 兼容旧拼写，建议使用 {@link #minuteAt(int...)}。
     * 
     * @param minutes 分钟值
     * @return 构建器
     */
    @Deprecated
    public TimeCronBuilder mintueAt(int... minutes) {
        return minuteAt(minutes);
    }

    public TimeCronBuilder hourAt(int... hours) {
        this.hours = hours;
        return this;
    }

    public TimeCronBuilder dayAt(int... days) {
        this.days = days;
        return this;
    }

    public TimeCronBuilder weekAt(int... weeks) {
        this.weeks = weeks;
        return this;
    }

    public List<TimeCron> build() {
        if (minutes == null || minutes.length < 1) {
            minutes = new int[] { SchedulerConstants.ANY };
        }
        if (hours == null || hours.length < 1) {
            hours = new int[] { SchedulerConstants.ANY };
        }
        if (days == null || days.length < 1) {
            days = new int[] { SchedulerConstants.ANY };
        }
        if (weeks == null || weeks.length < 1) {
            weeks = new int[] { SchedulerConstants.ANY };
        }
        int mSize = minutes.length;
        int hSize = hours.length;
        int dSize = days.length;
        int wSize = weeks.length;
        int size = mSize * hSize * dSize * wSize;

        List<TimeCron> result = new ArrayList<TimeCron>(size * 2);

        for (int m : minutes) {
            for (int h : hours) {
                for (int d : days) {
                    for (int w : weeks) {
                        result.add(new TimeCron(m, h, d, w));
                    }
                }
            }
        }

        return result;
    }
}
