package com.bytehonor.sdk.server.spring.getter;

import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class RequestKeyOpt {

    public static final String SPL = "__";

    public static final String EQ = SqlOperator.EQ.getKey();

    private String key;

    private String opt;

    public RequestKeyOpt() {
        this.key = "";
        this.opt = EQ;
    }
    
    public static String make(String key, String opt) {
        return new StringBuilder().append(key).append(SPL).append(opt).toString();
    }

    public static RequestKeyOpt parse(String raw) {
        RequestKeyOpt model = new RequestKeyOpt();
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
