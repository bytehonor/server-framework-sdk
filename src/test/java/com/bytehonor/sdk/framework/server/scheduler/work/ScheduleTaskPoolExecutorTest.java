package com.bytehonor.sdk.framework.server.scheduler.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.lang.thread.Sleep;

public class ScheduleTaskPoolExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleTaskPoolExecutorTest.class);

    @Test
    public void test() {
        // nThreads = 1 就得排队
        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                LOG.info("task1 begin");
                Sleep.millis(1000L);
                LOG.info("task1 end");
            }

        }, 1000L, TimeConstants.MINUTE);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                LOG.info("task2 begin");
                Sleep.millis(2000L);
                LOG.info("task2 end");
            }

        }, 1000L, TimeConstants.MINUTE);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                LOG.info("task3 begin");
                Sleep.millis(3000L);
                LOG.info("task3 end");
            }

        }, 1000L, TimeConstants.MINUTE);

        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                LOG.info("task4 begin");
                Sleep.millis(4000L);
                LOG.info("task4 end");
            }

        }, 1000L, TimeConstants.MINUTE);

        Sleep.millis(TimeConstants.HOUR);
    }

}
