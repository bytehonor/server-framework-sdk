package com.bytehonor.sdk.framework.server.scheduler.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.Sleep;
import com.bytehonor.sdk.framework.server.scheduler.ServerWorkScheduler;

public class ServerWorkTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkTest.class);

    @Test
    public void test() {

        ServerWork work1 = new ServerWork() {

            @Override
            public long intervals() {
                return TimeConstants.SECOND * 5;
            }

            @Override
            public void handle() {
                LOG.info("job1 begin");
                Sleep.millis(TimeConstants.SECOND * 2);
                LOG.info("job1 end");

            }
        };
        ServerWork work2 = new ServerWork() {

            @Override
            public long intervals() {

                return TimeConstants.SECOND * 30;
            }

            @Override
            public void handle() {
                LOG.info("job2 begin");
                Sleep.millis(TimeConstants.SECOND * 15);
                LOG.info("job2 end");

            }
        };

        ServerWorkScheduler.starter().add(work1).add(work2).start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
