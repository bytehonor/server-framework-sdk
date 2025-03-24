package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.plan.lock.SpringPlanLocker;

/**
 * @author lijianqiang
 *
 */
public class SpringPlanTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanTask.class);

    private final SpringPlanLocker locker;

    private SpringPlanTask(SpringPlanLocker locker) {
        this.locker = locker;
    }

    public static SpringPlanTask of(SpringPlanLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new SpringPlanTask(locker);
    }

    @Override
    public final void runInSafe() {
        Sleep.rand(1, 9);

        LocalDateTime ldt = LocalDateTime.now();

        // 检查锁
        if (locker.accept(ldt) == false) {
            return;
        }

        // 列出允许计划
        List<SpringPlan> plans = SpringPlanFactory.listPlanPlay();
        if (plans.isEmpty()) {
            LOG.debug("plans isEmpty");
            return;
        }

        for (SpringPlan plan : plans) {
            // 检查时间
            if (plan.accept(ldt) == false) {
                continue;
            }

            // 执行
            SpringPlanPoolExecutor.run(plan, ldt);
        }
    }

}
