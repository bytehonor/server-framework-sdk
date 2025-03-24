package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.SpringWorkTask;

public class ServerWorkSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkSchedulerTest.class);
    
    @Test
    public void test() {
        SpringWorkTask task = new SpringWorkTask() {

            @Override
            public long intervalMillis() {
                return 1000L;
            }

            @Override
            public void runInSafe() {
                LOG.info("runInSafe");
            }
            
        };
        
        ServerWorkScheduler.starter().with(task).start();
        
        Sleep.millis(9000L);
    }

}
