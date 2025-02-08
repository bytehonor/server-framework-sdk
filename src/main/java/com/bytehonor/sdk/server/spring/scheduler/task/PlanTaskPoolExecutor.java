package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadPoolBuilder;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanRecordCacheHolder;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlan;

/**
 * 
 * @author lijianqiang
 *
 */
public class PlanTaskPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(PlanTaskPoolExecutor.class);

    private static final String NAMED = "plan-task-thread-";

    private final ExecutorService executor;

    private PlanTaskPoolExecutor() {
        this.executor = ThreadPoolBuilder.half(NAMED);
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
        executor.execute(task);
    }
}
