package com.bytehonor.sdk.server.spring.scheduler.stats;

import com.bytehonor.sdk.server.spring.config.ServerConfig;

public class PlanStats {

    private final String key;

    private final String server;

    private final String name;

    private final Long time;

    public PlanStats(String name) {
        this.time = System.currentTimeMillis();
        this.name = name;
        this.server = ServerConfig.self().getId();
        this.key = new StringBuilder().append(this.name).append(":").append(this.time).toString();
    }

    public String getKey() {
        return key;
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
