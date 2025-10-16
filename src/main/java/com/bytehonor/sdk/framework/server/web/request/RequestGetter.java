package com.bytehonor.sdk.framework.server.web.request;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.constant.HttpConstants;
import com.bytehonor.sdk.framework.lang.constant.QueryLogic;
import com.bytehonor.sdk.framework.lang.core.KeyValueMap;
import com.bytehonor.sdk.framework.lang.getter.BooleanGetter;
import com.bytehonor.sdk.framework.lang.getter.IntegerGetter;
import com.bytehonor.sdk.framework.lang.getter.LongGetter;
import com.bytehonor.sdk.framework.lang.string.StringKit;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lijianqiang
 *
 */
public class RequestGetter {

    /**
     * @param request
     * @return
     */
    public static KeyValueMap map(HttpServletRequest request) {
        KeyValueMap map = new KeyValueMap();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            map.put(key, request.getParameter(key));
        }

        return map;
    }

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
        if (res > HttpConstants.LIMIT_MAX_TOP) {
            res = HttpConstants.LIMIT_MAX_TOP;
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
        Java.requireNonNull(val, key);
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
        Java.requireNonNull(val, key);
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
        Java.requireNonNull(val, key);
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
        Java.requireNonNull(request, "request");
        Java.requireNonNull(key, "key");
        return request.getParameter(key);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static String required(HttpServletRequest request, String key) {
        String val = optional(request, key);
        Java.requireNonNull(val, key);
        return val;
    }

    public static List<Long> longs(String src) {
        List<Long> list = new ArrayList<Long>();
        if (StringKit.isEmpty(src)) {
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
        if (StringKit.isEmpty(src)) {
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
        if (StringKit.isEmpty(src)) {
            return list;
        }
        String[] arr = src.split(",");
        for (String a : arr) {
            if (StringKit.isEmpty(a)) {
                continue;
            }
            list.add(a.trim());
        }
        return list;
    }
    
}
