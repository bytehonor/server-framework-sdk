package com.bytehonor.sdk.server.bytehonor.util;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.define.bytehonor.constant.HeaderKey;
import com.bytehonor.sdk.lang.bytehonor.string.StringObject;

public class TerminalUtils {

    public static String getFromTerminal(HttpServletRequest request) {
        String from = request.getParameter(HeaderKey.X_FROM_TERMINAL);
        if (StringObject.isEmpty(from)) {
            from = request.getHeader(HeaderKey.X_FROM_TERMINAL);
        }
        if (StringObject.isEmpty(from)) {
            from = "browser";
        }
        return from;
    }

    public static String getFromIp(HttpServletRequest request) {
        String ip = request.getHeader(HeaderKey.X_REAL_IP);
        if (StringObject.isEmpty(ip) == false) {
            return ip;
        }
        ip = request.getHeader(HeaderKey.X_FORWARDED_FOR);
        if (StringObject.isEmpty(ip) == false) {
            int at = ip.indexOf(",");
            if (at < 0) {
                return ip;
            }
            return ip.substring(0, at);
        }

        return request.getRemoteAddr();
    }

    public static String getFromUuid(HttpServletRequest request) {
        String from = request.getParameter(HeaderKey.X_FROM_UUID);
        if (StringObject.isEmpty(from)) {
            from = request.getHeader(HeaderKey.X_FROM_UUID);
        }
        return from;
    }
}
