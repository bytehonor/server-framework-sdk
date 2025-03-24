package com.bytehonor.sdk.server.spring.scheduler.plan;

import com.bytehonor.sdk.server.spring.web.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public class TimePlanStatus {

    private final String server;

    private final String name;

    private Boolean paused;

    private Long time;

    private String date;

    public TimePlanStatus(String name) {
        this.server = ServerContext.self().getId();
        this.name = name;
        this.paused = false;
        this.time = 0L;
        this.date = "";
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

}
