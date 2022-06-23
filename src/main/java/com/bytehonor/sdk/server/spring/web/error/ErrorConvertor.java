package com.bytehonor.sdk.server.spring.web.error;

import com.bytehonor.sdk.define.bytehonor.code.StandardCode;
import com.bytehonor.sdk.define.bytehonor.exception.StandardException;
import com.bytehonor.sdk.define.bytehonor.result.JsonResponse;

public class ErrorConvertor {

    public static JsonResponse<?> convert(Exception e) {
        if (e instanceof StandardException) {
            return defined((StandardException) e);
        }

        return any(e);
    }

    public static JsonResponse<?> defined(StandardException se) {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(se.getCode());
        jsonResponse.setMessage(ErrorMessage.format(se));
        return jsonResponse;
    }

    public static JsonResponse<?> any(Exception ex) {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.BAD_REQUEST);
        jsonResponse.setMessage(ErrorMessage.format(ex));
        return jsonResponse;
    }

    public static JsonResponse<?> bodyNull() {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.INTERNAL_ERROR);
        jsonResponse.setMessage("METHOD_RETURN_NULL");
        return jsonResponse;
    }

}
