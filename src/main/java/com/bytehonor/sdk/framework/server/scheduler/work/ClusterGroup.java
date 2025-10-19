package com.bytehonor.sdk.framework.server.scheduler.work;

import com.bytehonor.sdk.framework.lang.Java;

public abstract class ClusterGroup {

    private final ServerWorkFactory fatcory;

    public ClusterGroup() {
        this.fatcory = new ServerWorkFactory();
    }

    public final ClusterGroup add(ServerWork work) {
        Java.requireNonNull(work, "work");

        fatcory.add(work);
        return this;
    }

    /**
     * 主题
     * 
     * @return
     */
    public abstract String subject();

    public final void start() {
        this.fatcory.play();
    }
}
