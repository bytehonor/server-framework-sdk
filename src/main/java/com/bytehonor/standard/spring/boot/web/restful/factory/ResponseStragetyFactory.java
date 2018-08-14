package com.bytehonor.standard.spring.boot.web.restful.factory;

import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServerHttpResponse;

import com.bytehonor.standard.api.protocol.result.JsonResponse;
import com.bytehonor.standard.spring.boot.config.SpringBootStandardProperties;
import com.bytehonor.standard.spring.boot.web.error.exception.entity.ExceptionEntity;
import com.bytehonor.standard.spring.boot.web.restful.stragety.ResponseStragety;
import com.bytehonor.standard.spring.boot.web.restful.stragety.ReturnDirectResponseStragety;
import com.bytehonor.standard.spring.boot.web.restful.stragety.ReturnErrorResponseStragety;
import com.bytehonor.standard.spring.boot.web.restful.stragety.ReturnNormalResponseStragety;
import com.bytehonor.standard.spring.boot.web.restful.stragety.ReturnNullResponseStragety;

public class ResponseStragetyFactory {
	
	@SuppressWarnings("rawtypes")
	public static ResponseStragety build(Object body, ServerHttpResponse response, MethodParameter returnType, SpringBootStandardProperties standardSpringBootProperties) {
		if (body == null) {
			return new ReturnNullResponseStragety(response, standardSpringBootProperties);
		}

		if (body instanceof ExceptionEntity) {
			return new ReturnErrorResponseStragety((ExceptionEntity) body, response, standardSpringBootProperties);
		}
		
		if (body instanceof JsonResponse) {
			return new ReturnDirectResponseStragety((JsonResponse) body, response, standardSpringBootProperties);
		}

		
		return new ReturnNormalResponseStragety(body, response);
	}

}
