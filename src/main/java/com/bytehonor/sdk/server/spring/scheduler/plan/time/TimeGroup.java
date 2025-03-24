package com.bytehonor.sdk.server.spring.scheduler.plan.time;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.scheduler.constant.SchedulerConstants;

/**
 * 
 * @author lijianqiang
 *
 */
public class TimeGroup implements TimeCron {

    private final List<TimeCron> crons;

    public TimeGroup() {
        this.crons = new ArrayList<TimeCron>();
    }

    public TimeGroup(List<TimeCron> crons) {
        this.crons = crons;
    }

    @Override
    public boolean match(LocalDateTime ldt) {
        if (crons == null || crons.isEmpty()) {
            return false;
        }

        for (TimeCron cron : crons) {
            if (cron.match(ldt)) {
                return true;
            }
        }
        return false;
    }

    public List<TimeCron> getCrons() {
        return crons;
    }

    public void addCron(TimeCron cron) {
        this.crons.add(cron);
    }

    public static TimeGroupBuilder builder() {
        return new TimeGroupBuilder();
    }

    public static class TimeGroupBuilder {

        private final List<Integer> minutes;

        private final List<Integer> hours;

        private final List<Integer> days;

        private final List<TimeCron> list;

        private TimeGroupBuilder() {
            this.minutes = new ArrayList<Integer>(120);
            this.hours = new ArrayList<Integer>(48);
            this.days = new ArrayList<Integer>(60);
            this.list = new ArrayList<TimeCron>();
        }

        public TimeGroupBuilder every() {
            return every(0, 1);
        }

        public TimeGroupBuilder every(int from, int step) {
            if (from < 0 || from > 59) {
                throw new SpringServerException("error, from:" + from);
            }
            if (step < 1 || step > 59) {
                throw new SpringServerException("error, step:" + step);
            }
            minutes.clear(); // 先清
            if (step == 1) {
                minutes.add(SchedulerConstants.ANY);
                return this;
            }

            int value = from;
            for (int i = 0; i < 60; i++) {
                value = from + i * step;
                if (value > 59) {
                    break;
                }
                minutes.add(value);
            }

            return this;
        }

        /**
         * 链式操作中, 相同操作后面覆盖前面, 除非调用done方法
         * 
         * @param values
         * @return
         */
        public TimeGroupBuilder mintues(int... values) {
            minutes.clear(); // 先清
            for (int value : values) {
                this.minutes.add(value);
            }
            return this;
        }

        /**
         * 链式操作中, 相同操作后面覆盖前面, 除非调用done方法
         * 
         * @param values
         * @return
         */
        public TimeGroupBuilder hours(int... values) {
            hours.clear(); // 先清
            for (int value : values) {
                this.hours.add(value);
            }
            return this;
        }

        /**
         * 链式操作中, 相同操作后面覆盖前面, 除非调用done方法
         * 
         * @param values
         * @return
         */
        public TimeGroupBuilder days(int... values) {
            days.clear(); // 先清
            for (int value : values) {
                this.days.add(value);
            }
            return this;
        }

        public TimeGroupBuilder done() {
            if (isEmpty()) {
                return this;
            }

            if (minutes.isEmpty()) {
                minutes.add(SchedulerConstants.ANY);
            }
            if (hours.isEmpty()) {
                hours.add(SchedulerConstants.ANY);
            }
            if (days.isEmpty()) {
                days.add(SchedulerConstants.ANY);
            }

            for (int m : minutes) {
                for (int h : hours) {
                    for (int d : days) {
                        list.add(new DefineTimeCron(m, h, d));
                    }
                }
            }

            minutes.clear();
            hours.clear();
            days.clear();
            return this;
        }

        public TimeGroup build() {
            if (isEmpty() == false) {
                done();
            }

            if (list.isEmpty()) {
                throw new SpringServerException("no crons");
            }

            TimeGroup group = new TimeGroup();
            for (TimeCron item : list) {
                group.addCron(item);
            }

            list.clear();
            return group;
        }

        private boolean isEmpty() {
            return minutes.isEmpty() && hours.isEmpty() && days.isEmpty();
        }
    }
}
