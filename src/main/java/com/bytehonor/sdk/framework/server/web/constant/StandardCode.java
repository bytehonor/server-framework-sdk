package com.bytehonor.sdk.framework.server.web.constant;

/**
 * standard error code
 * 
 * @author lijianqiang
 *
 */
public class StandardCode {

    /**
     * OK
     */
    public static final int OK = 0;

    /**
     * SUCCESS
     */
    public static final String SUCCESS = "success";

    /**
     * FRAMEWORK_ERROR
     */
    public static final int FRAMEWORK_ERROR = 101;

    /**
     * INTERNAL_ERROR
     */
    public static final int INTERNAL_ERROR = 111;

    /**
     * 400 Bad Request
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 401 Unauthorized
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 403 Forbidden
     */
    public static final int FORBIDDEN = 403;

    /**
     * 404 Not Found
     */
    public static final int NOT_FOUND = 404;

    /**
     * FEIGN_FALLBACK
     */
    public static final int FEIGN_FALLBACK = 499;

    /**
     * UNDEFINED_ERROR
     */
    public static final int UNDEFINED_ERROR = 999;

    /**
     * TOKEN_EXPIRED
     */
    public static final int TOKEN_EXPIRED = 1001;
}
