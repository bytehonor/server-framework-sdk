package com.bytehonor.sdk.server.spring.scheduler.plan;

import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;
import com.bytehonor.sdk.server.spring.context.ServerContext;

public class TimePlanStats {

    private final String server;

    private final String name;

    private final Long time;

    private final String date;

    public TimePlanStats(String name) {
        this.server = ServerContext.self().getId();
        this.name = name;
        this.time = System.currentTimeMillis();
        this.date = LocalDateTimeUtils.format(this.time);
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

    public String getDate() {
        return date;
    }

}
