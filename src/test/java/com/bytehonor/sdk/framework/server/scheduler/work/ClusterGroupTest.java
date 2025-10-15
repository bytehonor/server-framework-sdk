package com.bytehonor.sdk.framework.server.scheduler.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.Sleep;
import com.bytehonor.sdk.framework.server.scheduler.work.lock.SpringWorkLocker;


public class ClusterGroupTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupTest.class);

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
            public void handle() {
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
            public void handle() {
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

        ClusterGroupFactory factory = new ClusterGroupFactory(TimeConstants.SECOND);
        factory.add(group);
        factory.init("testname", locker);
        
        factory.process();

        Sleep.millis(TimeConstants.MINUTE * 20);
    }
}
