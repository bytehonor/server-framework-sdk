package com.bytehonor.standard.spring.boot.web.restful.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.standard.api.protocol.result.JsonResponse;


public interface ResponseStragety {
	
	JsonResponse<?> process(ServerHttpRequest request);
}
