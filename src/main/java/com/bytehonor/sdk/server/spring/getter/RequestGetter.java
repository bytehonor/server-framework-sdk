package com.bytehonor.sdk.server.spring.getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.getter.BooleanGetter;
import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.LongGetter;
import com.bytehonor.sdk.lang.spring.string.SpringString;

/**
 * @author lijianqiang
 *
 */
public class RequestGetter {

    /**
     * @param request
     * @return
     */
    public static boolean counted(HttpServletRequest request) {
        return BooleanGetter.optional(optional(request, HttpConstants.COUNT_KEY), false);
    }

    /**
     * @param request
     * @return
     */
    public static int limit(HttpServletRequest request) {
        int res = IntegerGetter.optional(optional(request, HttpConstants.LIMIT_KEY), HttpConstants.LIMIT_DEF);
        if (res > HttpConstants.LIMIT_MAX) {
            res = HttpConstants.LIMIT_MAX;
        }
        return res;
    }

    /**
     * @param request
     * @return
     */
    public static int offset(HttpServletRequest request) {
        return IntegerGetter.optional(optional(request, HttpConstants.OFFSET_KEY), HttpConstants.OFFSET_DEF);
    }

    /**
     * @param request
     * @return
     */
    public static int page(HttpServletRequest request) {
        return IntegerGetter.optional(optional(request, HttpConstants.PAGE_KEY), HttpConstants.PAGE_DEF);
    }

    public static QueryLogic logic(HttpServletRequest request) {
        String key = optional(request, "logic");
        return QueryLogic.keyOf(key);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Integer integerOptional(HttpServletRequest request, String key) {
        return integerOptional(request, key, null);
    }

    /**
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static Integer integerOptional(HttpServletRequest request, String key, Integer def) {
        return IntegerGetter.optional(optional(request, key), def);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Integer integerRequried(HttpServletRequest request, String key) {
        Integer val = integerOptional(request, key);
        Objects.requireNonNull(val, key);
        return val;
    }

    /**
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static Long longOptional(HttpServletRequest request, String key, Long def) {
        return LongGetter.optional(optional(request, key), def);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Long longOptional(HttpServletRequest request, String key) {
        return longOptional(request, key, null);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Long longRequired(HttpServletRequest request, String key) {
        Long val = longOptional(request, key, null);
        Objects.requireNonNull(val, key);
        return val;
    }

    /**
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static Boolean booleanOptional(HttpServletRequest request, String key, Boolean def) {
        return BooleanGetter.optional(optional(request, key), def);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Boolean booleanOptional(HttpServletRequest request, String key) {
        return booleanOptional(request, key, null);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static Boolean booleanRequired(HttpServletRequest request, String key) {
        Boolean val = booleanOptional(request, key, null);
        Objects.requireNonNull(val, key);
        return val;
    }

    /**
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static String optional(HttpServletRequest request, String key, String def) {
        String val = optional(request, key);
        return val != null ? val : def;
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static String optional(HttpServletRequest request, String key) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(key, "key");
        return request.getParameter(key);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static String required(HttpServletRequest request, String key) {
        String val = optional(request, key);
        Objects.requireNonNull(val, key);
        return val;
    }

    public static List<Long> longs(String src) {
        List<Long> list = new ArrayList<Long>();
        if (SpringString.isEmpty(src)) {
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
        if (SpringString.isEmpty(src)) {
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
        if (SpringString.isEmpty(src)) {
            return list;
        }
        String[] arr = src.split(",");
        for (String a : arr) {
            if (SpringString.isEmpty(a)) {
                continue;
            }
            list.add(a.trim());
        }
        return list;
    }

}
