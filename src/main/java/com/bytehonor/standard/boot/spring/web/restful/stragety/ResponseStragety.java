package com.bytehonor.standard.boot.spring.web.restful.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.protocol.api.server.result.JsonResponse;


public interface ResponseStragety {
	
	JsonResponse<?> process(ServerHttpRequest request);
}
