package com.bytehonor.sdk.server.spring.scheduler;

import org.junit.Test;

import com.bytehonor.sdk.define.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.ThreadSleep;
import com.bytehonor.sdk.server.spring.scheduler.plan.PrintTimePlan;

public class SpringSchedulerTest {

    @Test
    public void testStart() {
        SpringScheduler.start();

        SpringScheduler.add(new PrintTimePlan());

        ThreadSleep.sleep(TimeConstants.MINUTE * 2);

        SpringScheduler.print();

        ThreadSleep.sleep(TimeConstants.MINUTE * 10);
    }

}
