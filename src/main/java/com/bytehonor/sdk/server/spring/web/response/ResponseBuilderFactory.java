package com.bytehonor.sdk.server.spring.web.response;

import java.util.Objects;

import com.bytehonor.sdk.define.spring.result.JsonResponse;
import com.bytehonor.sdk.server.spring.web.response.builder.DefaultResponseBuilder;
import com.bytehonor.sdk.server.spring.web.response.builder.JsonResponseBuilder;

public class ResponseBuilderFactory {

    public static ResponseBuilder get(Object body) {
        Objects.requireNonNull(body, "body");

        if (body instanceof JsonResponse) {
            return new JsonResponseBuilder();
        }

        return new DefaultResponseBuilder();
    }

}
