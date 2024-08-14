package com.bytehonor.sdk.server.spring.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;
import com.bytehonor.sdk.server.spring.scheduler.lock.CachePlanLocker;
import com.bytehonor.sdk.server.spring.scheduler.lock.PlanLocker;
import com.bytehonor.sdk.server.spring.scheduler.task.PlanTaskPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.task.TimePlanTask;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlan;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlanFactory;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlanStatus;
import com.bytehonor.sdk.server.spring.scheduler.util.SchedulerUtils;
import com.bytehonor.sdk.server.spring.work.ScheduleWorkPoolExecutor;

/**
 * 每分钟循环任务 启动类
 * 
 * @author lijianqiang
 *
 */
public class SpringScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SpringScheduler.class);

    public static void start() {
        start(0);
    }

    public static void start(int secondAt) {
        start(secondAt, new CachePlanLocker());
    }

    public static void start(PlanLocker locker) {
        start(0, locker);
    }

    public static void start(int secondAt, PlanLocker locker) {
        Objects.requireNonNull(locker, "locker");

        long delayMillis = SchedulerUtils.delayMillis(secondAt);
        LOG.info("locker:{}, delayMillis:{}, secondAt:{}", locker.getName(), delayMillis, secondAt);
        ScheduleWorkPoolExecutor.schedule(TimePlanTask.of(locker), delayMillis, TimeConstants.MINUTE);
    }

    public static List<TimePlanStatus> plans() {
        return TimePlanFactory.listPlanStatus();
    }

    public static TimePlanStatus detail(String name) {
        return TimePlanFactory.getPlanStatus(name);
    }

    public static void add(TimePlan plan) {
        TimePlanFactory.add(plan);
    }

    public static void pause(String name) {
        PlanPauseCacheHolder.pause(name);
    }

    public static void play(String name) {
        PlanPauseCacheHolder.play(name);
    }

    public static void run(String name) {
        TimePlan plan = TimePlanFactory.required(name);
        PlanTaskPoolExecutor.run(plan, LocalDateTime.now());
    }

    public static void print(String name) {
        TimePlan plan = TimePlanFactory.required(name);
        plan.print();
    }
}
