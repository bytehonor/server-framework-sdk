package com.bytehonor.sdk.server.spring.jdbc;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.lang.string.StringCreator;

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
        target[at++] = TA;
        for (int i = 0; i < len; i++) {
            if (TA == src.charAt(i)) {
                find = true;
                target[at++] = '\\';
                target[at++] = TA;
            } else {
                target[at++] = src.charAt(i);
            }
        }
        target[at++] = TA;
        if (find) {
            return new String(target, 0, at);
        }
        return StringCreator.create().append(TA).append(src).append(TA).toString();
    }
    
    public static String column(String src) {
        if (StringUtils.isEmpty(src)) {
            return src;
        }
        int len = src.length();
        char[] target = new char[len * 2];
        boolean find = false;
        int at = 0;
        for (int i = 0; i < len; i++) {
            if (';' == src.charAt(i)) {
                find = true;
                continue;
            }
            target[at++] = src.charAt(i);
        }
        if (find) {
            return new String(target, 0, at);
        }
        return src;
    }
}
