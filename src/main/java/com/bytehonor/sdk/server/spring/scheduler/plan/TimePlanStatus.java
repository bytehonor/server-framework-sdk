package com.bytehonor.sdk.server.spring.scheduler.plan;

import com.bytehonor.sdk.server.spring.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public class TimePlanStatus {

    private final String server;

    private final String name;

    private final Boolean paused;

    public TimePlanStatus(String name, Boolean paused) {
        this.server = ServerContext.self().getId();
        this.name = name;
        this.paused = paused;
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

    public Boolean getPaused() {
        return paused;
    }

}
