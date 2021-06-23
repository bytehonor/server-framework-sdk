package com.bytehonor.sdk.server.bytehonor.util;

import javax.servlet.http.HttpServletRequest;

public class TerminalUtils {

    public static String getFromTerminal(HttpServletRequest request) {
        return GlobalValueUtils.getFromTerminal(request);
    }

    public static String getFromIp(HttpServletRequest request) {
        return GlobalValueUtils.getFromIp(request);
    }
}
