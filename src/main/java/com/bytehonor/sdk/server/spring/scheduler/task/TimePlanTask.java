package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.lock.PlanLocker;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlan;
import com.bytehonor.sdk.server.spring.scheduler.time.TimePlanFactory;

/**
 * @author lijianqiang
 *
 */
public class TimePlanTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanTask.class);

    private final PlanLocker locker;

    private TimePlanTask(PlanLocker locker) {
        this.locker = locker;
    }

    public static TimePlanTask of(PlanLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new TimePlanTask(locker);
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
        List<TimePlan> plans = TimePlanFactory.listPlanPlay();
        if (plans.isEmpty()) {
            LOG.debug("plans isEmpty");
            return;
        }

        for (TimePlan plan : plans) {
            // 检查时间
            if (plan.accept(ldt) == false) {
                continue;
            }

            // 执行
            PlanTaskPoolExecutor.run(plan, ldt);
        }
    }

}
