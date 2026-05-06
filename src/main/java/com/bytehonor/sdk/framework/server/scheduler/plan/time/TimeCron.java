package com.bytehonor.sdk.framework.server.scheduler.plan.time;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bytehonor.sdk.framework.server.exception.SpringServerException;
import com.bytehonor.sdk.framework.server.scheduler.constant.SchedulerConstants;

/**
 * 周/日/时/分 4级匹配
 * 
 * @author lijianqiang
 *
 */
public final class TimeCron implements Serializable {

    private static final long serialVersionUID = -9160168602633077811L;

    private static final int ANY = SchedulerConstants.ANY;

    private final int minute;

    private final int hour;

    private final int day;

    private final int week;

    public TimeCron() {
        this(ANY, ANY, ANY, ANY);
    }

    public TimeCron(int minute, int hour, int day, int week) {
        validOrThrow(minute, ANY, 0, 59, "minute");
        validOrThrow(hour, ANY, 0, 23, "hour");
        validOrThrow(day, ANY, 1, 31, "day");
        validOrThrow(week, ANY, 1, 7, "week");
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.week = week;
    }

    private static void validOrThrow(int value, int any, int min, int max, String name) {
        if (value == any) {
            return;
        }
        if (value < min || value > max) {
            throw new SpringServerException("invalid " + name + ":" + value);
        }
    }

    public boolean match(LocalDateTime ldt) {
        if (ldt == null) {
            return false;
        }
        if (ANY != this.week && ldt.getDayOfWeek().getValue() != this.week) {
            return false;
        }
        if (ANY != this.day && ldt.getDayOfMonth() != this.day) {
            return false;
        }
        if (ANY != this.hour && ldt.getHour() != this.hour) {
            return false;
        }
        if (ANY != this.minute && ldt.getMinute() != this.minute) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("week=").append(show(week)).append(", day=").append(show(day))
                .append(", hour=").append(show(hour)).append(", minute=").append(show(minute)).toString();
    }

    private static String show(int value) {
        if (value == ANY) {
            return "*";
        }
        return String.valueOf(value);
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getWeek() {
        return week;
    }

}
