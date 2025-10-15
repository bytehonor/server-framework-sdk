package com.bytehonor.sdk.framework.server.scheduler;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.scheduler.work.ClusterGroup;
import com.bytehonor.sdk.framework.server.scheduler.work.ClusterGroupFactory;
import com.bytehonor.sdk.framework.server.scheduler.work.lock.SpringWorkLocker;

/**
 * <pre>
 * 集群group
 * </pre>
 * 
 * @author lijianqiang
 */
public final class ClusterGroupScheduler {
    
    private static final Logger LOG = LoggerFactory.getLogger(ClusterGroupScheduler.class);
    
    private static final long DELAYS = TimeConstants.SECOND * 5;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final ClusterGroupFactory factory;
    private final long delays;
    private final long intervals;

    private ClusterGroupScheduler() {
        this.factory = new ClusterGroupFactory(INTERVALS * 2);
        this.delays = DELAYS;
        this.intervals = INTERVALS;
    }

    private static class LazyHolder {
        private static ClusterGroupScheduler SINGLE = new ClusterGroupScheduler();
    }

    private static ClusterGroupScheduler self() {
        return LazyHolder.SINGLE;
    }
    
    private void start() {
        if (factory.isEmpty()) {
            LOG.warn("competitor empty");
            return;
        }
        
        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                process();
            }

        }, delays, intervals);
    }

    private void process() {
        factory.process();
    }

    public static Starter starter(String server, SpringWorkLocker locker) {
        return new Starter(server, locker);
    }

    public static class Starter {

        private Starter(String server, SpringWorkLocker locker) {
            self().factory.init(server, locker);
        }

        public Starter add(ClusterGroup group) {
            Objects.requireNonNull(group, "group");
            
            self().factory.add(group);
            return this;
        }

        public void start() {
            self().start();
        }
    }
}
