package com.bytehonor.sdk.server.spring.web.restful.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.protocol.core.server.result.JsonResponse;


public interface ResponseStragety {
	
	JsonResponse<?> process(ServerHttpRequest request);
}
