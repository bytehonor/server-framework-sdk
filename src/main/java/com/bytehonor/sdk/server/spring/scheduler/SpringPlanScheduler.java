package com.bytehonor.sdk.server.spring.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;
import com.bytehonor.sdk.server.spring.scheduler.lock.CachePlanLocker;
import com.bytehonor.sdk.server.spring.scheduler.lock.PlanLocker;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlan;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanFactory;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanStatus;
import com.bytehonor.sdk.server.spring.scheduler.plan.SpringPlanTask;
import com.bytehonor.sdk.server.spring.scheduler.util.SchedulerUtils;

/**
 * 每分钟循环任务 启动类
 * 
 * @author lijianqiang
 *
 */
public class SpringPlanScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanScheduler.class);

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
        ScheduleTaskPoolExecutor.schedule(SpringPlanTask.of(locker), delayMillis, TimeConstants.MINUTE);
    }

    public static List<SpringPlanStatus> plans() {
        return SpringPlanFactory.listPlanStatus();
    }

    public static SpringPlanStatus detail(String name) {
        return SpringPlanFactory.getPlanStatus(name);
    }

    public static void add(SpringPlan plan) {
        SpringPlanFactory.add(plan);
    }

    public static void pause(String name) {
        PlanPauseCacheHolder.pause(name);
    }

    public static void play(String name) {
        PlanPauseCacheHolder.play(name);
    }

    public static void run(String name) {
        SpringPlan plan = SpringPlanFactory.required(name);
        SpringPlanPoolExecutor.run(plan, LocalDateTime.now());
    }

    public static void print(String name) {
        SpringPlan plan = SpringPlanFactory.required(name);
        plan.print();
    }
}
