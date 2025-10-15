package com.bytehonor.sdk.framework.server.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.scheduler.plan.SpringPlan;
import com.bytehonor.sdk.framework.server.scheduler.plan.SpringPlanFactory;
import com.bytehonor.sdk.framework.server.scheduler.plan.SpringPlanPoolExecutor;
import com.bytehonor.sdk.framework.server.scheduler.plan.SpringPlanStatus;
import com.bytehonor.sdk.framework.server.scheduler.plan.SpringPlanTask;
import com.bytehonor.sdk.framework.server.scheduler.plan.cache.SpringPlanPauseCache;
import com.bytehonor.sdk.framework.server.scheduler.plan.lock.CachePlanLocker;
import com.bytehonor.sdk.framework.server.scheduler.plan.lock.SpringPlanLocker;
import com.bytehonor.sdk.framework.server.scheduler.plan.util.SpringPlanUtils;

/**
 * <pre>
 * 分布式plan
 * 
 * 每分钟循环任务 启动类
 * 
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public final class SpringPlanScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanScheduler.class);

    public static void start() {
        start(0);
    }

    public static void start(int secondAt) {
        start(secondAt, new CachePlanLocker());
    }

    public static void start(SpringPlanLocker locker) {
        start(0, locker);
    }

    public static void start(int secondAt, SpringPlanLocker locker) {
        Java.requireNonNull(locker, "locker");

        long delayMillis = SpringPlanUtils.delayMillis(secondAt);
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
        SpringPlanPauseCache.pause(name);
    }

    public static void play(String name) {
        SpringPlanPauseCache.play(name);
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
