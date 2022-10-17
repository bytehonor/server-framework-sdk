package com.bytehonor.sdk.server.spring.getter;

import java.util.List;

import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;

public class KeyAction {

    private String key;

    private String action;

    public KeyAction() {
        this("", "eq");
    }

    public KeyAction(String key) {
        this(key, "eq");
    }

    public KeyAction(String key, String action) {
        this.key = key;
        this.action = action;
    }

    public static KeyAction parse(String raw) {
        List<String> list = StringSplitUtils.split(raw, '.');
        int size = list.size();
        if (size < 1) {
            return new KeyAction();
        }
        if (size == 1) {
            return new KeyAction(list.get(0));
        }
        return new KeyAction(list.get(0), list.get(1));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
