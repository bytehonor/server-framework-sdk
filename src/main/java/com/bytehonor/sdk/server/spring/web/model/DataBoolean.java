package com.bytehonor.sdk.server.spring.web.model;

import java.io.Serializable;

/**
 * @author lijianqiang
 *
 */
public class DataBoolean implements Serializable {

    private static final long serialVersionUID = 8002495835746801370L;

    private Boolean result;

    public static DataBoolean of(Boolean result) {
        return new DataBoolean(result);
    }

    public DataBoolean(Boolean result) {
        this.result = result;
    }

    public DataBoolean() {
        this(false);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
