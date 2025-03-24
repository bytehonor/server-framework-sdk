package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.lang.spring.Java;

public abstract class ClusterWork implements SpringWork {

    private final List<SpringWorkTask> tasks;
    
    public ClusterWork() {
        this.tasks = new ArrayList<SpringWorkTask>();
    }

    @Override
    public final List<SpringWorkTask> tasks() {
        return tasks;
    }

    public final boolean isEmpty() {
        return tasks.isEmpty();
    }
    
    public final ClusterWork add(SpringWorkTask task) {
        Java.requireNonNull(task, "task");

        tasks.add(task);
        return this;
    }
    
    /**
     * 主题
     * 
     * @return
     */
    public abstract String subject();
}
