package com.bytehonor.sdk.framework.server.web.response;

import com.bytehonor.sdk.framework.concept.response.JsonResponse;
import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.server.web.response.builder.DefaultResponseBuilder;
import com.bytehonor.sdk.framework.server.web.response.builder.JsonResponseBuilder;

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
