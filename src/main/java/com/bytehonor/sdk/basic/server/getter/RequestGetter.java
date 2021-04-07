package com.bytehonor.sdk.basic.server.getter;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.basic.define.constant.HttpConstants;

/**
 * @author lijianqiang
 *
 */
public class RequestGetter {

    /**
     * @param request
     * @return
     */
    public static boolean isCount(HttpServletRequest request) {
        return BooleanGetter.optional(getValue(request, HttpConstants.COUNT_KEY), false);
    }

    /**
     * @param request
     * @return
     */
    public static int getLimit(HttpServletRequest request) {
        int res = IntegerGetter.optional(getValue(request, HttpConstants.LIMIT_KEY), HttpConstants.LIMIT_DEF);
        if (res > HttpConstants.LIMIT_MAX) {
            res = HttpConstants.LIMIT_MAX;
        }
        return res;
    }

    /**
     * @param request
     * @return
     */
    public static int getOffset(HttpServletRequest request) {
        return IntegerGetter.optional(getValue(request, HttpConstants.OFFSET_KEY), HttpConstants.OFFSET_DEFAULT);
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
        return IntegerGetter.optional(getValue(request, key), def);
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
        return LongGetter.optional(getValue(request, key), def);
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
        return BooleanGetter.optional(getValue(request, key), def);
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
     * @return
     */
    private static String getValue(HttpServletRequest request, String key) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(key, "key");
        return request.getParameter(key);
    }

    /**
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static String stringOptional(HttpServletRequest request, String key, String def) {
        String val = getValue(request, key);
        return StringGetter.optional(val, def);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static String stringOptional(HttpServletRequest request, String key) {
        return stringOptional(request, key, null);
    }

    /**
     * @param request
     * @param key
     * @return
     */
    public static String stringRequired(HttpServletRequest request, String key) {
        String val = stringOptional(request, key, null);
        Objects.requireNonNull(val, key);
        return val;
    }
}
