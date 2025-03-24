package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.base.spring.response.JsonResponse;
import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.web.response.builder.DefaultResponseBuilder;
import com.bytehonor.sdk.server.spring.web.response.builder.JsonResponseBuilder;

/**
 * @author lijianqiang
 *
 */
public class ResponseBuilderFactory {

    public static ResponseBuilder get(Object body) {
        Java.requireNonNull(body, "body");

        if (body instanceof JsonResponse) {
            return new JsonResponseBuilder();
        }

        return new DefaultResponseBuilder();
    }

}
