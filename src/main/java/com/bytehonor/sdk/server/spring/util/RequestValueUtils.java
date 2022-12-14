package com.bytehonor.sdk.server.spring.util;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.define.spring.constant.OauthConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class RequestValueUtils {

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
//        ip = request.getHeader(HeaderKey.X_FORWARDED_FOR);
//        if (SpringString.isEmpty(ip) == false) {
//            int at = ip.indexOf(",");
//            if (at < 0) {
//                return ip;
//            }
//            return ip.substring(0, at);
//        }

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
