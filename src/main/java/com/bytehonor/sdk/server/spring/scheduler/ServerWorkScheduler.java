package com.bytehonor.sdk.server.spring.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.ServerWork;
import com.bytehonor.sdk.server.spring.scheduler.work.ServerWorkFactory;

/**
 * <pre>
 * 单点work
 * </pre>
 * 
 * @author lijianqiang
 */
public final class ServerWorkScheduler {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkScheduler.class);

    private static final long DELAYS = TimeConstants.SECOND * 2;

    private final ServerWorkFactory factory;

    private ServerWorkScheduler() {
        this.factory = new ServerWorkFactory();
    }

    private static class LazyHolder {
        private static ServerWorkScheduler SINGLE = new ServerWorkScheduler();
    }

    private static ServerWorkScheduler self() {
        return LazyHolder.SINGLE;
    }

    private void start() {
        if (factory.isEmpty()) {
            LOG.warn("factory empty");
            return;
        }
        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                process();
            }

        }, DELAYS);
    }
    
    private void process() {
        factory.run();
    }
    
    public static Starter starter() {
        return new Starter();
    }

    public static class Starter {

        private Starter() {
        }

        public Starter add(ServerWork work) {
            Java.requireNonNull(work, "work");
            
            self().factory.add(work);
            return this;
        }

        public void start() {
            self().start();
        }
    }
}
