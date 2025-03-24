package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterWork;
import com.bytehonor.sdk.server.spring.scheduler.work.SpringWorkTask;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

public class ClusterWorkSchedulerTest {

private static final Logger LOG = LoggerFactory.getLogger(ClusterWorkSchedulerTest.class);
    
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
        
        ClusterWork work = new ClusterWork() {

            @Override
            public String subject() {
                return "ClusterWork1";
            }
            
        };
        
        work.add(task);
        
        String server = "test";
        SpringWorkLocker locker = new SpringWorkLocker() {
            
            @Override
            public boolean lock(String key, String value, long millis) {
                return true;
            }
            
            @Override
            public String which(String key) {
                return server;
            }
            
            @Override
            public void expireAt(String key, long timestamp) {
                
            }
        };
        
        ClusterWorkScheduler.starter(server, locker).with(work).start();
        
        Sleep.millis(60000L);
    }

}
