package com.bytehonor.sdk.server.spring.jdbc.update;

public class UpdateColumn {
    private String key;

    private Object value;

    public UpdateColumn() {
        this(null, null);
    }

    public UpdateColumn(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static UpdateColumn of(String key, Object value) {
        return new UpdateColumn(key, value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
