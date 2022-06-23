package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.define.bytehonor.result.JsonResponse;

public class ResponseConvertor {

    public static JsonResponse<?> convert(Object body) {
        ResponseBuilder builder = ResponseBuilderFactory.get(body);
        return builder.build(body);
    }
}
