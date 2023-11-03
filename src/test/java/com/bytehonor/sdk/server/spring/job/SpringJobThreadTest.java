package com.bytehonor.sdk.server.spring.job;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;

public class SpringJobThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringJobThreadTest.class);

    @Test
    public void test() {
        SpringJobLocker locker = new SpringJobLocker() {

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

        SpringJob job1 = new SpringJob() {

            @Override
            public String target() {
                return "job2";
            }

            @Override
            public void start() {
                LOG.info("job2 run");
            }
        };
        SpringJob job2 = new SpringJob() {

            @Override
            public String target() {
                return "job1";
            }

            @Override
            public void start() {
                LOG.info("job1 run");
            }
        };

        SpringJobThread thread = SpringJobThread.builder().name("test").locker(locker).job(job1).job(job2).build();
        thread.start();

        Sleep.millis(TimeConstants.MINUTE * 10);
    }
}
