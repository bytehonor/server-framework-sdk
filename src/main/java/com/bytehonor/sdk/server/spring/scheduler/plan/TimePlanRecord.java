package com.bytehonor.sdk.server.spring.scheduler.plan;

import com.bytehonor.sdk.server.spring.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public class TimePlanRecord {

    private final String server;

    private final String name;

    private final Long time;

    public TimePlanRecord(String name) {
        this.server = ServerContext.self().getId();
        this.name = name;
        this.time = System.currentTimeMillis();
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time;
    }
}
