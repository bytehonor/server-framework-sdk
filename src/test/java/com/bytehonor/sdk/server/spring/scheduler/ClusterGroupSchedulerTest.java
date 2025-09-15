package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterGroup;
import com.bytehonor.sdk.server.spring.scheduler.work.ServerWork;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

public class ClusterGroupSchedulerTest {

private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupSchedulerTest.class);
    
    @Test
    public void test() {
        ServerWork task = new ServerWork() {

            @Override
            public long intervals() {
                return 1000L;
            }

            @Override
            public void runInSafe() {
                LOG.info("work run");
            }
            
        };
        
        ClusterGroup group = new ClusterGroup() {

            @Override
            public String subject() {
                return "ClusterGroup";
            }
            
        };
        
        group.add(task);
        
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
        
        ClusterGroupScheduler.starter(server, locker).with(group).start();
        
        Sleep.millis(60000L);
    }

}
