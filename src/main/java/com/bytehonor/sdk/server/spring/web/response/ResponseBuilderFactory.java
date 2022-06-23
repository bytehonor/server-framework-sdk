package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.define.spring.result.JsonResponse;
import com.bytehonor.sdk.server.spring.web.response.builder.DefaultResponseBuilder;
import com.bytehonor.sdk.server.spring.web.response.builder.JsonResponseBuilder;
import com.bytehonor.sdk.server.spring.web.response.builder.NullResponseBuilder;

public class ResponseBuilderFactory {

    public static ResponseBuilder get(Object body) {
        if (body == null) {
            return new NullResponseBuilder();
        }

        if (body instanceof JsonResponse) {
            return new JsonResponseBuilder();
        }

        return new DefaultResponseBuilder();
    }

}
