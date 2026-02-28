package com.bytehonor.sdk.framework.server.web.request;

import com.bytehonor.sdk.concept.applet.constant.OauthConstants;
import com.bytehonor.sdk.framework.lang.string.StringKit;

import jakarta.servlet.http.HttpServletRequest;

public class RequestSession {

    public static String getTerminal(HttpServletRequest request) {
        String from = request.getHeader(OauthConstants.REQUEST_TERMINAL);
        if (StringKit.isEmpty(from)) {
            from = "browser";
        }
        return from;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(OauthConstants.REQUEST_IP);
        if (StringKit.isEmpty(ip) == false) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getUuid(HttpServletRequest request) {
        String val = request.getHeader(OauthConstants.REQUEST_UUID);
        if (StringKit.isEmpty(val)) {
            val = request.getParameter("uuid");
        }
        return val;
    }
}
