package com.bytehonor.sdk.server.spring.web.model;

import java.io.Serializable;

/**
 * @author lijianqiang
 *
 */
public class DataAccept implements Serializable {

    private static final long serialVersionUID = 3133545192736146299L;

    private String server;

    private Boolean status;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
