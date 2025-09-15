package com.bytehonor.sdk.server.spring.scheduler.work;

import com.bytehonor.sdk.lang.spring.Java;

public abstract class SubjectGroup {

    private final LoopTaskFactory factory;
    
    public SubjectGroup() {
        this.factory = new LoopTaskFactory();
    }

    public final LoopTaskFactory factory() {
        return factory;
    }

    public final SubjectGroup add(LoopTask task) {
        Java.requireNonNull(task, "task");

        factory.add(task);
        return this;
    }
    
    /**
     * 主题
     * 
     * @return
     */
    public abstract String subject();
}
