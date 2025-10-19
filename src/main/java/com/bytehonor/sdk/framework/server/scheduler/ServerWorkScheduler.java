package com.bytehonor.sdk.framework.server.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.constant.TimeConstants;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.scheduler.work.ServerWork;
import com.bytehonor.sdk.framework.server.scheduler.work.ServerWorkFactory;

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

    private void doStart() {
        ScheduleTaskPoolExecutor.schedule(new SafeTask() {

            @Override
            public void handle() {
                doHandle();
            }

        }, DELAYS);
    }

    private void doHandle() {
        if (factory.isEmpty()) {
            LOG.warn("worker empty");
            return;
        }

        factory.play();
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
            self().doStart();
        }
    }
}
