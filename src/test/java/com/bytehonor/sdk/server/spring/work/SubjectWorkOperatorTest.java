package com.bytehonor.sdk.server.spring.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class SubjectWorkOperatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectWorkOperatorTest.class);

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

        SubjectWork job1 = new SubjectWork() {

            @Override
            public String subject() {
                return "job1";
            }

            @Override
            public void start() {
                LOG.info("job1 run");
            }
        };
        SubjectWork job2 = new SubjectWork() {

            @Override
            public String subject() {
                return "job2";
            }

            @Override
            public void start() {
                LOG.info("job2 run");
            }
        };

        SubjectWorkOperator thread = new SubjectWorkOperator("testname", locker);
        thread.add(job1);
        thread.add(job2);
        thread.start();

        Sleep.millis(TimeConstants.MINUTE * 10);
    }

}
