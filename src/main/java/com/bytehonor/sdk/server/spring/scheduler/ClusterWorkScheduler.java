package com.bytehonor.sdk.server.spring.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterWork;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterWorkExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

/**
 * <pre>
 * 集群work
 * </pre>
 * 
 * @author lijianqiang
 */
public class ClusterWorkScheduler {

    private ClusterWorkExecutor executor;
    
    private ClusterWorkScheduler() {
    }
    
    private static class LazyHolder {
        private static ClusterWorkScheduler SINGLE = new ClusterWorkScheduler();
    }

    private static ClusterWorkScheduler self() {
        return LazyHolder.SINGLE;
    }
    
    private void init(String server, SpringWorkLocker locker, List<ClusterWork> works) {
        Java.requireNonNull(locker, "locker");
        if (CollectionUtils.isEmpty(works)) {
            throw new RuntimeException("works empty");
        }
        
        executor = new ClusterWorkExecutor(server, locker);
        for (ClusterWork work : works) {
            executor.add(work);
        }
        
        executor.start();
    }    
    
    public static Starter starter(String server, SpringWorkLocker locker) {
        return new Starter(server, locker);
    }
    
    public static class Starter {
        
        private final String server;
        
        private final SpringWorkLocker locker;

        private final List<ClusterWork> works;
        
        private Starter(String server ,SpringWorkLocker locker) {
            this.server = server;
            this.locker = locker;
            this.works = new ArrayList<ClusterWork>();
        }
        
        public Starter with(ClusterWork work) {
            works.add(work);
            return this;
        }
        
        public void start() {
            self().init(server, locker, works);
        }
    }
}
