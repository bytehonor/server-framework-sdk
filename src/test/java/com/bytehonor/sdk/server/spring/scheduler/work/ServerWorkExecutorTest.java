package com.bytehonor.sdk.server.spring.scheduler.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class ServerWorkExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkExecutorTest.class);

    @Test
    public void test() {

        LoopTask task1 = new LoopTask() {

            @Override
            public long intervals() {
                return TimeConstants.SECOND * 5;
            }

            @Override
            public void runInSafe() {
                LOG.info("job1 begin");
                Sleep.millis(TimeConstants.SECOND * 2);
                LOG.info("job1 end");

            }
        };
        LoopTask task2 = new LoopTask() {

            @Override
            public long intervals() {

                return TimeConstants.SECOND * 30;
            }

            @Override
            public void runInSafe() {
                LOG.info("job2 begin");
                Sleep.millis(TimeConstants.SECOND * 15);
                LOG.info("job2 end");

            }
        };

        ServerWorkExecutor scheduler = new ServerWorkExecutor();
        scheduler.add(task1);
        scheduler.add(task2);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
