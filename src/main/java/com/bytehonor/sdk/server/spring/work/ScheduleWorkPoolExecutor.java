package com.bytehonor.sdk.server.spring.work;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public class ScheduleWorkPoolExecutor {

    private static final String NAMED = "schedule-work-thread-";

    private final ScheduledExecutorService service;

    private ScheduleWorkPoolExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newScheduledThreadPool(nThreads, new CustomizableThreadFactory(NAMED));
    }

    private static class LazyHolder {
        private static ScheduleWorkPoolExecutor SINGLE = new ScheduleWorkPoolExecutor();
    }

    private static ScheduleWorkPoolExecutor self() {
        return LazyHolder.SINGLE;
    }

    private void scheduleAtFixedRate(SafeTask task, long delayMillis, long intervalMillis) {
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(task, delayMillis, intervalMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 毫秒
     * 
     * @param task
     * @param delayMillis
     * @param intervalMillis
     */
    public static void schedule(SafeTask task, long delayMillis, long intervalMillis) {
        Objects.requireNonNull(task, "task");

        self().scheduleAtFixedRate(task, delayMillis, intervalMillis);
    }
}
