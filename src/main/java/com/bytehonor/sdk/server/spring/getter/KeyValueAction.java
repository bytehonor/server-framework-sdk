package com.bytehonor.sdk.server.spring.getter;

import java.util.List;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;

public class KeyValueAction {

    private String key;

    private String value;

    private String action;

    public KeyValueAction() {
        this("", "", "");
    }

    public KeyValueAction(String key, String value, String action) {
        this.key = key;
        this.value = value;
        this.action = action;
    }

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

    public static KeyValueAction parse(String raw, String value) {
        if (SpringString.isEmpty(raw)) {
            return new KeyValueAction();
        }

        List<String> list = StringSplitUtils.split(raw, '.');
        int size = list.size();
        if (size == 1) {
            return new KeyValueAction(list.get(0), "eq", value);
        }

        return new KeyValueAction(list.get(0), list.get(1), value);
    }

}
