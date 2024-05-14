package com.bytehonor.sdk.server.spring.web.model;

import java.io.Serializable;

/**
 * @author lijianqiang
 *
 */
public class DataLong implements Serializable {

    private static final long serialVersionUID = -6318191886336616923L;

    private Long result;

    public static DataLong of(Long result) {
        return new DataLong(result);
    }

    public DataLong(Long result) {
        this.result = result;
    }

    public DataLong() {
        this(null);
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

}
