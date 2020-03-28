package com.bytehonor.sdk.basic.server.getter;

import com.bytehonor.sdk.basic.lang.string.StringCreator;
import com.bytehonor.sdk.basic.server.exception.ParameterExcption;

/**
 * @author lijianqiang
 *
 */
public class IntegerGetter {

    public static Integer parse(String src) {
        try {
            return Integer.valueOf(src);
        } catch (Exception e) {
            throw new ParameterExcption(StringCreator.create().append(src).append(" is not number ").toString());
        }
    }

    public static Integer optional(String src) {
        return optional(src, null);
    }

    public static Integer optional(String src, Integer def) {
        if (src == null) {
            return def;
        }
        try {
            return parse(src);
        } catch (Exception e) {
            return def;
        }
    }

    public static Integer optional(Integer src, Integer def) {
        return src != null ? src : def;
    }
}
