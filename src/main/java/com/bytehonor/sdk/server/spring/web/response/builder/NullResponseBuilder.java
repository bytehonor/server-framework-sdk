package com.bytehonor.sdk.server.spring.web.response.builder;

import com.bytehonor.sdk.define.bytehonor.result.JsonResponse;
import com.bytehonor.sdk.server.spring.web.error.ErrorConvertor;
import com.bytehonor.sdk.server.spring.web.response.ResponseBuilder;

public final class NullResponseBuilder implements ResponseBuilder {

    @Override
    public JsonResponse<?> build(Object body) {
        return ErrorConvertor.bodyNull();
    }
}
