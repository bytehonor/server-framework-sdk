package com.bytehonor.sdk.framework.server.web.request;

import com.bytehonor.sdk.framework.core.constant.OauthConstants;
import com.bytehonor.sdk.framework.lang.string.SpringString;

import jakarta.servlet.http.HttpServletRequest;

public class RequestSession {

    public static String getTerminal(HttpServletRequest request) {
        String from = request.getHeader(OauthConstants.REQUEST_TERMINAL);
        if (SpringString.isEmpty(from)) {
            from = "browser";
        }
        return from;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(OauthConstants.REQUEST_IP);
        if (SpringString.isEmpty(ip) == false) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getUuid(HttpServletRequest request) {
        String val = request.getHeader(OauthConstants.REQUEST_UUID);
        if (SpringString.isEmpty(val)) {
            val = request.getParameter("uuid");
        }
        return val;
    }
}
