package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

import com.bytehonor.sdk.server.spring.scheduler.constant.SchedulerConstants;

/**
 * 日/时/分 三级匹配
 * 
 * @author lijianqiang
 *
 */
public class DefineTimeCron implements TimeCron {

    private static final int ANY = SchedulerConstants.ANY;

    private int minute;

    private int hour;

    private int day;

    public DefineTimeCron() {
        this(ANY, ANY, ANY);
    }

    public DefineTimeCron(int minute, int hour, int day) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
    }

    @Override
    public boolean match(LocalDateTime ldt) {
        if (ldt == null) {
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
        return new StringBuilder().append(day).append(":").append(hour).append(":").append(minute).toString();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
