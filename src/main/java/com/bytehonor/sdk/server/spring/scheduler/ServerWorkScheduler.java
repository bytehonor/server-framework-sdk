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
    
    public static Starter starter() {
        return new Starter();
    }
    
    public static class Starter {
        
        private Starter() {
        }
        
        public Starter with(SpringWorkTask task) {
            self().executor.add(task);
            return this;
        }
        
        public void start() {
            self().executor.start();
        }
    }
}
