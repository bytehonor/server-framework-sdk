package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

/**
 * 单点模式，仅有一个work
 * 
 * @author lijianqiang
 *
 */
public class ServerWorkExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkExecutor.class);

    private static final long DELAYS = TimeConstants.SECOND * 3;

    private final long delayMillis;
    private final ServerWork work;

    public ServerWorkExecutor() {
        this(DELAYS);
    }

    public ServerWorkExecutor(long delayMillis) {
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

    public ServerWorkExecutor add(SpringWorkTask task) {
        Java.requireNonNull(task, "task");

        work.add(task);

        return this;
    }

    private void doWork() {
        try {
            List<SpringWorkTask> tasks = work.tasks();
            LOG.info("doWork tasks:{}", tasks.size());
            for (SpringWorkTask task : tasks) {
                task.start();
                Sleep.millis(200L);
            }
        } catch (Exception e) {
            LOG.error("doWork error", e);
        }
    }
}
