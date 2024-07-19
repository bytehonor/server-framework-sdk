package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanRecordCacheHolder;

/**
 * 
 * @author lijianqiang
 *
 */
public class PlanTaskPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(PlanTaskPoolExecutor.class);

    private static final String NAMED = "plan-task-thread-";

    private final ExecutorService service;

    private PlanTaskPoolExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newFixedThreadPool(nThreads, new CustomizableThreadFactory(NAMED));
    }

    private static class LazyHolder {
        private static PlanTaskPoolExecutor SINGLE = new PlanTaskPoolExecutor();
    }

    private static PlanTaskPoolExecutor self() {
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
