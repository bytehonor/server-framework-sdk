package com.bytehonor.sdk.server.spring.scheduler.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class SpringWorkServerSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkServerSchedulerTest.class);

    @Test
    public void test() {

        SpringWorkTask job1 = new SpringWorkTask() {

            @Override
            public String subject() {
                return "job1";
            }

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
        SpringWorkTask job2 = new SpringWorkTask() {

            @Override
            public String subject() {
                return "job2";
            }

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

        SpringWorkServerScheduler scheduler = new SpringWorkServerScheduler();
        scheduler.add(job1);
        scheduler.add(job2);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
