package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;

import com.bytehonor.sdk.define.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.cache.CacheLocker;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;

public class SchedulerPlanStarterTest {

    @Test
    public void testStart() {
        SchedulerPlanStarter.start(new TaskLocker() {

            @Override
            public boolean lock(String key) {

                return CacheLocker.lock(key);
            }
        });

        ThreadSleep.sleep(TimeConstants.MINUTE * 10);
    }

}
