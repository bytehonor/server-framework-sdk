package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.ServerWork;

public class ServerWorkSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkSchedulerTest.class);
    
    @Test
    public void test() {
        ServerWork work = new ServerWork() {

            @Override
            public long intervals() {
                return 1000L;
            }

            @Override
            public void runInSafe() {
                LOG.info("runInSafe");
            }
            
        };
        
        ServerWorkScheduler.starter().add(work).start();
        
        Sleep.millis(9000L);
    }

}
