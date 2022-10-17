package com.bytehonor.sdk.server.spring.getter;

public class KeyValueAction {

    private String key;

    private String value;

    private String action;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static KeyValueAction parse(String raw, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

}
