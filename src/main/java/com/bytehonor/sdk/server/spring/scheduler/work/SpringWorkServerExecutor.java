package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;

/**
 * 本地承担任务，有多少任务启动多少
 * 
 * @author lijianqiang
 *
 */
public class SpringWorkServerExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkServerExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 3;

    private final long delayMillis;
    private final ServerWork work;

    public SpringWorkServerExecutor() {
        this(DELAYS);
    }

    public SpringWorkServerExecutor(long delayMillis) {
        this.delayMillis = delayMillis;
        this.work = new ServerWork();
    }

    public void start() {
        if (work.isEmpty()) {
            LOG.warn("work empty");
            return;
        }

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                doWork();
            }

        }, delayMillis);
    }

    public SpringWorkServerExecutor add(SpringWorkTask task) {
        Objects.requireNonNull(task, "task");

        work.add(task);

        return this;
    }

    private void doWork() {
        try {
            List<SpringWorkTask> tasks = work.tasks();
            LOG.info("begin tasks:{}", tasks.size());
            for (SpringWorkTask task : tasks) {
                task.start();
            }
        } catch (Exception e) {
            LOG.error("doWork error", e);
        }
    }
}
