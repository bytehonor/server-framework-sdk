package com.bytehonor.sdk.server.spring.exception;

import com.bytehonor.sdk.define.spring.code.StandardCode;
import com.bytehonor.sdk.define.spring.exception.StandardException;
import com.bytehonor.sdk.define.spring.response.JsonResponse;

/**
 * @author lijianqiang
 *
 */
public class ErrorConvertor {

    public static JsonResponse<?> convert(Exception e) {
        if (e instanceof StandardException) {
            return standard((StandardException) e);
        }

        return bad(e);
    }

    /**
     * 有具体定义的
     * 
     * @param se
     * @return
     */
    private static JsonResponse<?> standard(StandardException se) {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(se.getCode());
        jsonResponse.setMessage(format(se));
        return jsonResponse;
    }

    /**
     * 未定义的
     * 
     * @param ex
     * @return
     */
    private static JsonResponse<?> bad(Exception ex) {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.BAD_REQUEST);
        jsonResponse.setMessage(format(ex));
        return jsonResponse;
    }

    /**
     * @param ex
     * @return
     */
    public static String format(Exception ex) {
        StringBuilder sb = new StringBuilder();
        if (!(ex instanceof StandardException)) {
            sb.append(ex.getClass().getSimpleName()).append(":");
        }
        sb.append(ex.getMessage());
        return sb.toString();
    }
}
