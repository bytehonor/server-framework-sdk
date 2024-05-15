package com.bytehonor.sdk.server.spring.web.response;

import java.util.Objects;

import com.bytehonor.sdk.base.spring.response.JsonResponse;
import com.bytehonor.sdk.server.spring.web.response.builder.DefaultResponseBuilder;
import com.bytehonor.sdk.server.spring.web.response.builder.JsonResponseBuilder;

/**
 * @author lijianqiang
 *
 */
public class ResponseBuilderFactory {

    public static ResponseBuilder get(Object body) {
        Objects.requireNonNull(body, "body");

        if (body instanceof JsonResponse) {
            return new JsonResponseBuilder();
        }

        return new DefaultResponseBuilder();
    }

}
