package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

public class ClusterGroupExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupExecutorTest.class);

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

        ServerWork work1 = new ServerWork() {

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
        ServerWork work2 = new ServerWork() {

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
        
        ClusterGroup group = new ClusterGroup() {

            @Override
            public String subject() {
                return "ClusterWork1";
            }
            
        };
        
        group.add(work1);
        group.add(work2);

        ClusterGroupExecutor scheduler = new ClusterGroupExecutor("testname", locker);
        scheduler.add(group);
        scheduler.start();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }

}
