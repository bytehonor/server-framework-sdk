package com.bytehonor.sdk.server.spring.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.SpringServer;
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

    private final List<ClusterWork> works;
    
    private ClusterWorkExecutor executor;
    
    private ClusterWorkScheduler() {
        this.works = new ArrayList<ClusterWork>();
    }
    
    private static class LazyHolder {
        private static ClusterWorkScheduler SINGLE = new ClusterWorkScheduler();
    }

    private static ClusterWorkScheduler self() {
        return LazyHolder.SINGLE;
    }
    
    public static void start(SpringWorkLocker locker) {
        self().doStart(locker);
    }
    
    public static void add(ClusterWork work) {
        self().works.add(work);
    }
    
    private void doStart(SpringWorkLocker locker) {
        Java.requireNonNull(locker, "locker");
        if (CollectionUtils.isEmpty(works)) {
            throw new RuntimeException("works empty");
        }
        
        executor = new ClusterWorkExecutor(SpringServer.id(), locker);
        for (ClusterWork work : works) {
            executor.add(work);
        }
        executor.start();
    }    
}
