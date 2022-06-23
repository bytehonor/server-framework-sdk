package com.bytehonor.sdk.server.spring.web.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.spring.code.StandardCode;
import com.bytehonor.sdk.define.spring.result.JsonResponse;

public class ResponseConvertor {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseConvertor.class);

    public static JsonResponse<?> convert(Object body) {
        if (body == null) {
            bodyNull();
        }

        try {
            ResponseBuilder builder = ResponseBuilderFactory.get(body);
            return builder.build(body);
        } catch (Exception e) {
            LOG.error("build error", e);
            return error();
        }

    }

    public static JsonResponse<?> bodyNull() {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.INTERNAL_ERROR);
        jsonResponse.setMessage("METHOD_RETURN_NULL");
        return jsonResponse;
    }

    public static JsonResponse<?> error() {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.FRAMEWORK_ERROR);
        jsonResponse.setMessage("SystemError");
        return jsonResponse;
    }
}
