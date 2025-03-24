package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

public class SpringWorkTaskTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWorkTaskTest.class);

    @Test
    public void test() {
        SpringWorkLocker locker = new SpringWorkLocker() {

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

        SpringWorkTask job1 = new SpringWorkTask() {

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
        SpringWorkTask job2 = new SpringWorkTask() {

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

        SubjectWorkOperator operator = new SubjectWorkOperator("testname", locker);
        operator.add(job1);
        operator.add(job2);
        operator.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
