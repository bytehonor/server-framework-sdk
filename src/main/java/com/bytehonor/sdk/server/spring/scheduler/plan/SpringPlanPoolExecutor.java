package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadPoolBuilder;
import com.bytehonor.sdk.server.spring.scheduler.plan.cache.SpringPlanRecordCache;

/**
 * 
 * @author lijianqiang
 *
 */
public class SpringPlanPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanPoolExecutor.class);

    private static final String NAMED = "spring-plan-thread-";

    private final ExecutorService executor;

    private SpringPlanPoolExecutor() {
        this.executor = ThreadPoolBuilder.half(NAMED);
    }

    private static class LazyHolder {
        private static SpringPlanPoolExecutor SINGLE = new SpringPlanPoolExecutor();
    }

    private static SpringPlanPoolExecutor self() {
        return LazyHolder.SINGLE;
    }

    /**
     * @param plan
     * @param ldt
     */
    public static void run(SpringPlan plan, LocalDateTime ldt) {
        Objects.requireNonNull(plan, "plan");
        Objects.requireNonNull(ldt, "ldt");

        String name = plan.getClass().getSimpleName();
        LOG.info("name:{} run", name);

        // 加入执行
        add(plan.create(ldt));

        // 加入记录
        SpringPlanRecordCache.add(name);
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
