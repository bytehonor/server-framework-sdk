package com.bytehonor.sdk.basic.server.web.response;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.sdk.basic.define.result.JsonResponse;

public interface ResponseStragety {

	JsonResponse<?> process(ServerHttpRequest request);
}
