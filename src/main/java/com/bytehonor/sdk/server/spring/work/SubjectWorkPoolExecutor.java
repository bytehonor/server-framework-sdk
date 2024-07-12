package com.bytehonor.sdk.server.spring.work;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public class SubjectWorkPoolExecutor {

    private final ScheduledExecutorService service;

    private SubjectWorkPoolExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newScheduledThreadPool(nThreads);
    }

    private static class LazyHolder {
        private static SubjectWorkPoolExecutor SINGLE = new SubjectWorkPoolExecutor();
    }

    private static SubjectWorkPoolExecutor self() {
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
