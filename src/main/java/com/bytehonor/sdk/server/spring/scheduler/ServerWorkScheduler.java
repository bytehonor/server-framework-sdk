package com.bytehonor.sdk.server.spring.scheduler;

import com.bytehonor.sdk.server.spring.scheduler.work.ServerWorkExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.SpringWorkTask;

/**
 * <pre>
 * 单点work
 * </pre>
 * 
 * @author lijianqiang
 */
public class ServerWorkScheduler {

    private final ServerWorkExecutor executor;
    
    private ServerWorkScheduler() {
        this.executor = new ServerWorkExecutor();
    }
    
    private static class LazyHolder {
        private static ServerWorkScheduler SINGLE = new ServerWorkScheduler();
    }

    private static ServerWorkScheduler self() {
        return LazyHolder.SINGLE;
    }
    
    public static void start() {
        self().executor.start();
    }
    
    public static void add(SpringWorkTask task) {
        self().executor.add(task);
    }
}
