package com.bytehonor.sdk.server.spring.scheduler.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;

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
    
    private final LoopTaskFactory factory;

    public ServerWorkExecutor() {
        this(DELAYS);
    }

    public ServerWorkExecutor(long delayMillis) {
        this.delayMillis = delayMillis;
        this.factory = new LoopTaskFactory();
    }

    public void start() {
        if (factory.isEmpty()) {
            LOG.warn("work empty");
            return;
        }

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                doStart();
            }

        }, delayMillis);
    }

    public ServerWorkExecutor add(LoopTask task) {
        Java.requireNonNull(task, "task");

        factory.add(task);

        return this;
    }

    private void doStart() {
        factory.run();
    }
}
