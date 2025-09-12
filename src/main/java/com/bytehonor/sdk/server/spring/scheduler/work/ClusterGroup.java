package com.bytehonor.sdk.server.spring.scheduler.work;

import com.bytehonor.sdk.lang.spring.Java;

public abstract class ClusterGroup {

    private final ServerWorkFactory factory;
    
    public ClusterGroup() {
        this.factory = new ServerWorkFactory();
    }

    public final ServerWorkFactory factory() {
        return factory;
    }

    public final ClusterGroup add(ServerWork work) {
        Java.requireNonNull(work, "work");

        factory.add(work);
        return this;
    }
    
    /**
     * 主题
     * 
     * @return
     */
    public abstract String subject();
}
