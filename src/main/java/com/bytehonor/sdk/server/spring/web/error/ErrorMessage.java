package com.bytehonor.sdk.server.spring.web.error;

public class ErrorMessage {

    public static String format(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getClass().getSimpleName()).append(":").append(ex.getMessage());
        return sb.toString();
    }

}
