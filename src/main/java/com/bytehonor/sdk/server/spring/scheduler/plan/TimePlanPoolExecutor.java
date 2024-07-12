package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanRecordCacheHolder;

/**
 * 单线程执行
 * 
 * @author lijianqiang
 *
 */
public class TimePlanPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanPoolExecutor.class);

    private final ExecutorService service;

    private TimePlanPoolExecutor() {
        // int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newSingleThreadExecutor();
    }

    private static class LazyHolder {
        private static TimePlanPoolExecutor SINGLE = new TimePlanPoolExecutor();
    }

    private static TimePlanPoolExecutor self() {
        return LazyHolder.SINGLE;
    }

    /**
     * @param plan
     * @param ldt
     */
    public static void run(TimePlan plan, LocalDateTime ldt) {
        Objects.requireNonNull(plan, "plan");
        Objects.requireNonNull(ldt, "ldt");

        String name = plan.getClass().getSimpleName();
        LOG.info("name:{} run", name);

        // 加入执行
        add(plan.create(ldt));

        // 加入记录
        PlanRecordCacheHolder.add(name);
    }

    /**
     * 
     * @param task
     */
    public static void add(SafeTask task) {
        Objects.requireNonNull(task, "task");

        self().execute(task);
    }

    private void execute(SafeTask task) {
        service.execute(task);
    }
}
