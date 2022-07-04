package com.bytehonor.sdk.server.spring.scheduler;

import java.time.LocalTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskExecutor;
import com.bytehonor.sdk.server.spring.scheduler.lock.CacheSchedulerLocker;
import com.bytehonor.sdk.server.spring.scheduler.lock.SchedulerLocker;
import com.bytehonor.sdk.server.spring.scheduler.task.PeriodTask;

/**
 * 每分钟循环任务 启动类
 * 
 * @author lijianqiang
 *
 */
public class SchedulerPlanStarter {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerPlanStarter.class);

    private static final long PERIOD_SECONDS = 60L;

    public static void start() {
        start(0, new CacheSchedulerLocker());
    }

    public static void start(SchedulerLocker locker) {
        start(0, locker);
    }

    public static void start(int secondAt, SchedulerLocker locker) {
        Objects.requireNonNull(locker, "locker");

        int secondNow = LocalTime.now().getSecond();
        long delays = delaySeconds(secondAt, secondNow);
        LOG.info("locker:{}, delays:{}, secondAt:{}, secondNow:{}", locker.getName(), delays, secondAt, secondNow);
        ScheduleTaskExecutor.schedule(new PeriodTask(locker), delays, PERIOD_SECONDS);
    }

    private static long delaySeconds(int secondAt, int secondNow) {
        int delaySeconds = secondAt - secondNow;
        if (delaySeconds < 0) {
            delaySeconds += 60;
        }
        return delaySeconds;
    }

}
