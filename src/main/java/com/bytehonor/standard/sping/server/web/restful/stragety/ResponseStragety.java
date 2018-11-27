package com.bytehonor.standard.sping.server.web.restful.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.bytehonor.protocol.common.server.result.JsonResponse;


public interface ResponseStragety {
	
	JsonResponse<?> process(ServerHttpRequest request);
}
