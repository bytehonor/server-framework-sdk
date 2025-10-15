package com.bytehonor.sdk.framework.server.web.request;

import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.string.SpringString;

public class KeyOptPair {

    public static final String SPL = "__";

    public static final String EQ = SqlOperator.EQ.getKey();

    private String key;

    private String opt;

    public KeyOptPair() {
        this.key = "";
        this.opt = EQ;
    }

    public static String make(String key, String opt) {
        return new StringBuilder().append(key).append(SPL).append(opt).toString();
    }

    public static KeyOptPair parse(String raw) {
        KeyOptPair model = new KeyOptPair();
        if (SpringString.isEmpty(raw)) {
            return model;
        }
        int diff = 2;
        int at = raw.indexOf(SPL);
        if (at > 1) {
            int length = raw.length();
            model.setKey(raw.substring(0, at));
            model.setOpt(raw.substring(at + diff, length));
        } else {
            model.setKey(raw);
        }
        return model;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

}
