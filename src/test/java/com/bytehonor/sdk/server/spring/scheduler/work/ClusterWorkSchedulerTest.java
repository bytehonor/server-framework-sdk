package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class ClusterWorkSchedulerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterWorkSchedulerTest.class);

    @Test
    public void test() {
        SubjectLocker locker = new SubjectLocker() {

            private Map<String, String> map = new HashMap<String, String>();

            @Override
            public boolean lock(String key, String value, long millis) {
                map.put(key, value);
                return true;
            }

            @Override
            public String get(String key) {
                return map.get(key);
            }

            @Override
            public void expireAt(String key, long timestamp) {

            }
        };

        SubjectTask job1 = new SubjectTask() {

            @Override
            public String subject() {
                return "job1";
            }

            @Override
            public long intervalMillis() {

                return TimeConstants.SECOND * 10;
            }

            @Override
            public void runInSafe() {
                LOG.info("job1 begin");
                Sleep.millis(TimeConstants.SECOND * 5);
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

                return TimeConstants.SECOND * 15;
            }

            @Override
            public void runInSafe() {
                LOG.info("job2 begin");
                Sleep.millis(TimeConstants.SECOND * 10);
                LOG.info("job2 end");

            }
        };

        ClusterWorkScheduler scheduler = new ClusterWorkScheduler("testname", locker);
        scheduler.add(job1);
        scheduler.add(job2);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
