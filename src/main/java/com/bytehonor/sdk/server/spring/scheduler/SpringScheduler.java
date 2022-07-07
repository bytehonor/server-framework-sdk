package com.bytehonor.sdk.server.spring.scheduler;

import java.time.LocalTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SpringScheduleExecutor;
import com.bytehonor.sdk.server.spring.scheduler.factory.TimePlanFactory;
import com.bytehonor.sdk.server.spring.scheduler.lock.CacheTaskLocker;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlan;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanTask;
import com.bytehonor.sdk.server.spring.scheduler.stats.PlanStatsHandler;
import com.bytehonor.sdk.server.spring.scheduler.util.SchedulerUtils;

/**
 * 每分钟循环任务 启动类
 * 
 * @author lijianqiang
 *
 */
public class SpringScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SpringScheduler.class);

    private static final long PERIOD_SECONDS = 60L;

    public static void start() {
        start(0);
    }

    public static void start(int secondAt) {
        start(secondAt, new CacheTaskLocker());
    }

    public static void start(TaskLocker locker) {
        start(0, locker);
    }

    public static void start(int secondAt, TaskLocker locker) {
        Objects.requireNonNull(locker, "locker");

        int secondNow = LocalTime.now().getSecond();
        long delays = SchedulerUtils.delaySeconds(secondAt, secondNow);
        LOG.info("locker:{}, delays:{}, secondAt:{}, secondNow:{}", locker.getName(), delays, secondAt, secondNow);
        SpringScheduleExecutor.schedule(TimePlanTask.of(locker), delays, PERIOD_SECONDS);
    }

    public static void add(TimePlan plan) {
        TimePlanFactory.add(plan);
    }

    public static void print() {
        PlanStatsHandler.print();
    }
}
