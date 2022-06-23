package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.define.bytehonor.result.JsonResponse;

public interface ResponseBuilder {

	JsonResponse<?> build(Object body);
}
