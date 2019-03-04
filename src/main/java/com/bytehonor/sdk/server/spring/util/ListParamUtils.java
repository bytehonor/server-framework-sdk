package com.bytehonor.sdk.server.spring.util;

import java.util.List;

public class ListParamUtils {

    private static final String CON = ",";

    public static String joinString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String val : list) {
            sb.append("'").append(val).append("',");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinLong(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long val : list) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    public static String joinInteger(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer val : list) {
            sb.append(val).append(CON);
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

}
