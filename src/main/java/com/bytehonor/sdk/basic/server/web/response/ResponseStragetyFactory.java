package com.bytehonor.sdk.basic.server.web.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServerHttpResponse;

import com.bytehonor.sdk.basic.server.config.SpringBootStandardProperties;
import com.bytehonor.sdk.basic.server.web.error.ExceptionHolder;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnDirectResponseStragety;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnErrorResponseStragety;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnNormalResponseStragety;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnNullResponseStragety;
import com.bytehonor.sdk.protocol.common.result.JsonResponse;

public class ResponseStragetyFactory {

	@SuppressWarnings("rawtypes")
	public static ResponseStragety build(Object body, ServerHttpResponse response, MethodParameter returnType,
			SpringBootStandardProperties standardSpringBootProperties) {
		if (body == null) {
			return new ReturnNullResponseStragety(response, standardSpringBootProperties);
		}

		if (body instanceof ExceptionHolder) {
			return new ReturnErrorResponseStragety((ExceptionHolder) body, response, standardSpringBootProperties);
		}

		if (body instanceof JsonResponse) {
			return new ReturnDirectResponseStragety((JsonResponse) body, response, standardSpringBootProperties);
		}

		return new ReturnNormalResponseStragety(body, response);
	}

}
