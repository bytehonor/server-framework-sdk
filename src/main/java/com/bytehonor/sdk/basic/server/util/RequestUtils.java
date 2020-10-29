package com.bytehonor.sdk.basic.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.server.getter.RequestGetter;

public class RequestUtils {
    
    public static String string(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    public static String stringRequired(HttpServletRequest request, String key) {
        String val = request.getParameter(key);
        Objects.requireNonNull(val, key);
        if (val.length() < 1) {
            throw new NullPointerException(key);
        }
        return val;
    }

    public static Long longRequired(HttpServletRequest request, String key) {
        String val = request.getParameter(key);
        Objects.requireNonNull(val, key);
        try {
            return Long.valueOf(val);
        } catch (Exception e) {
            throw new IllegalArgumentException(key + "=" + val);
        }
    }

    public static Long longOptional(HttpServletRequest request, String key) {
        return longOptional(request, key, null);
    }

    public static Long longOptional(HttpServletRequest request, String key, Long def) {
        String val = request.getParameter(key);
        if (StringUtils.isEmpty(val)) {
            return def;
        }
        try {
            return Long.valueOf(val);
        } catch (Exception e) {
            return def;
        }
    }

    public static Integer integerRequired(HttpServletRequest request, String key) {
        String val = request.getParameter(key);
        Objects.requireNonNull(val, key);
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            throw new IllegalArgumentException(key + "=" + val);
        }
    }

    public static Integer integerOptional(HttpServletRequest request, String key) {
        return integerOptional(request, key, null);
    }

    public static Integer integerOptional(HttpServletRequest request, String key, Integer def) {
        String val = request.getParameter(key);
        if (StringUtils.isEmpty(val)) {
            return def;
        }
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            return def;
        }
    }

    public static int limit(HttpServletRequest request) {
        return RequestGetter.getLimit(request);
    }

    public static int offset(HttpServletRequest request) {
        return RequestGetter.getOffset(request);
    }

    public static List<Long> longs(String src) {
        List<Long> list = new ArrayList<Long>();
        if (StringUtils.isEmpty(src)) {
            return list;
        }
        String[] arr = src.split(",");
        for (String a : arr) {
            list.add(Long.valueOf(a));
        }
        return list;
    }

    public static List<Integer> integers(String src) {
        List<Integer> list = new ArrayList<Integer>();
        if (StringUtils.isEmpty(src)) {
            return list;
        }
        String[] arr = src.split(",");
        for (String a : arr) {
            list.add(Integer.valueOf(a));
        }
        return list;
    }

    public static List<String> strings(String src) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(src)) {
            return list;
        }
        String[] arr = src.split(",");
        for (String a : arr) {
            if (StringUtils.isEmpty(a)) {
                continue;
            }
            list.add(a.trim());
        }
        return list;
    }
}
