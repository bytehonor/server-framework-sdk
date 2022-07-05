package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

public class OnceTimeCron implements TimeCron {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    @Override
    public boolean match(LocalDateTime ldt) {
        if (year != ldt.getYear()) {
            return false;
        }
        if (month != ldt.getMonthValue()) {
            return false;
        }
        if (day != ldt.getDayOfMonth()) {
            return false;
        }
        if (hour != ldt.getHour()) {
            return false;
        }
        if (minute != ldt.getMinute()) {
            return false;
        }
        return true;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}
