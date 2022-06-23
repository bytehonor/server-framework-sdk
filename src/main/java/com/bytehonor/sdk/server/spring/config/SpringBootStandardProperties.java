package com.bytehonor.sdk.server.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "standard.springboot")
public class SpringBootStandardProperties {

    /**
     * Server Api Resfult Http Status force 200, default true
     */
    private boolean forceHttpStatus = true;

    /**
     * Server Print Error Trace Lines, default 32
     */
    private int errorTraceLines = 32;

    public boolean isForceHttpStatus() {
        return forceHttpStatus;
    }

    public void setForceHttpStatus(boolean forceHttpStatus) {
        this.forceHttpStatus = forceHttpStatus;
    }

    public int getErrorTraceLines() {
        return errorTraceLines;
    }

    public void setErrorTraceLines(int errorTraceLines) {
        this.errorTraceLines = errorTraceLines;
    }

}
