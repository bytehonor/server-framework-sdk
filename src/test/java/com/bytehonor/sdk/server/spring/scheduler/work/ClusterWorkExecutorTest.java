package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

public class ClusterWorkExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterWorkExecutorTest.class);

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
            public String which(String key) {
                return map.get(key);
            }

            @Override
            public void expireAt(String key, long timestamp) {

            }
        };

        LoopTask task1 = new LoopTask() {

            @Override
            public long intervals() {

                return TimeConstants.SECOND * 10;
            }

            @Override
            public void runInSafe() {
                LOG.info("job1 begin");
                Sleep.millis(TimeConstants.SECOND * 5);
                LOG.info("job1 end");

            }
        };
        LoopTask task2 = new LoopTask() {

            @Override
            public long intervals() {

                return TimeConstants.SECOND * 15;
            }

            @Override
            public void runInSafe() {
                LOG.info("job2 begin");
                Sleep.millis(TimeConstants.SECOND * 10);
                LOG.info("job2 end");

            }
        };
        
        SubjectGroup group = new SubjectGroup() {

            @Override
            public String subject() {
                return "ClusterWork1";
            }
            
        };
        
        group.add(task1);
        group.add(task2);

        ClusterWorkExecutor scheduler = new ClusterWorkExecutor("testname", locker);
        scheduler.add(group);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
