package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;

/**
 * @author lijianqiang
 *
 */
public class TimePlanTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanTask.class);

    private final TaskLocker locker;

    private TimePlanTask(TaskLocker locker) {
        this.locker = locker;
    }

    public static TimePlanTask of(TaskLocker locker) {
        Objects.requireNonNull(locker, "locker");

        return new TimePlanTask(locker);
    }

    @Override
    public final void runInSafe() {
        ThreadSleep.rand(1, 9);

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
            TimePlanExecutor.run(plan, ldt);
        }
    }

}
