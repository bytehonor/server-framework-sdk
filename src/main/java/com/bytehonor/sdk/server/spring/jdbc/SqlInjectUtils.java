package com.bytehonor.sdk.server.spring.jdbc;

import org.apache.commons.lang3.StringUtils;

public class SqlInjectUtils {

    private static final char TA = '\'';

    public static String escape(String src) {
        if (StringUtils.isEmpty(src)) {
            return src;
        }
        int len = src.length();
        char[] target = new char[len * 2];
        boolean find = false;
        int at = 0;
        for (int i = 0; i < len; i++) {
            if (TA == src.charAt(i)) {
                find = true;
                target[at++] = '\\';
                target[at++] = TA;
            } else {
                target[at++] = src.charAt(i);
            }
        }

        if (find) {
            return new String(target, 0, at);
        }
        return src;
    }
}
