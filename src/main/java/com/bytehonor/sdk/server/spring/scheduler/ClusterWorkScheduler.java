package com.bytehonor.sdk.server.spring.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterGroup;
import com.bytehonor.sdk.server.spring.scheduler.work.ClusterWorkExecutor;
import com.bytehonor.sdk.server.spring.scheduler.work.lock.SpringWorkLocker;

/**
 * <pre>
 * 集群group
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

    private void init(String server, SpringWorkLocker locker, List<ClusterGroup> groups) {
        Java.requireNonNull(server, "server");
        Java.requireNonNull(locker, "locker");

        if (CollectionUtils.isEmpty(groups)) {
            throw new RuntimeException("groups empty");
        }

        executor = new ClusterWorkExecutor(server, locker);
        for (ClusterGroup group : groups) {
            executor.add(group);
        }

        executor.start();
    }

    public static Starter starter(String server, SpringWorkLocker locker) {
        return new Starter(server, locker);
    }

    public static class Starter {

        private final String server;

        private final SpringWorkLocker locker;

        private final List<ClusterGroup> groups;

        private Starter(String server, SpringWorkLocker locker) {
            this.server = server;
            this.locker = locker;
            this.groups = new ArrayList<ClusterGroup>();
        }

        public Starter with(ClusterGroup group) {
            groups.add(group);
            return this;
        }

        public void start() {
            self().init(server, locker, groups);
        }
    }
}
