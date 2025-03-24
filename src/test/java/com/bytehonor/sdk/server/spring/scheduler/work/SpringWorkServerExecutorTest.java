package com.bytehonor.sdk.server.spring.scheduler.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class SpringWorkServerExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkServerExecutorTest.class);

    @Test
    public void test() {

        SpringWorkTask task1 = new SpringWorkTask() {

            @Override
            public long intervalMillis() {
                return TimeConstants.SECOND * 5;
            }

            @Override
            public void runInSafe() {
                LOG.info("job1 begin");
                Sleep.millis(TimeConstants.SECOND * 2);
                LOG.info("job1 end");

            }
        };
        SpringWorkTask task2 = new SpringWorkTask() {

            @Override
            public long intervalMillis() {

                return TimeConstants.SECOND * 30;
            }

            @Override
            public void runInSafe() {
                LOG.info("job2 begin");
                Sleep.millis(TimeConstants.SECOND * 15);
                LOG.info("job2 end");

            }
        };

        SpringWorkServerExecutor scheduler = new SpringWorkServerExecutor();
        scheduler.add(task1);
        scheduler.add(task2);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
