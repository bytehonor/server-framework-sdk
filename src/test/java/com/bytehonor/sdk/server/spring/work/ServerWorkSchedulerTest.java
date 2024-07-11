package com.bytehonor.sdk.server.spring.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class ServerWorkSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkSchedulerTest.class);

    @Test
    public void test() {

        SubjectTask job1 = new SubjectTask() {

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
        SubjectTask job2 = new SubjectTask() {

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

        ServerWorkScheduler scheduler = new ServerWorkScheduler();
        scheduler.add(job1);
        scheduler.add(job2);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
