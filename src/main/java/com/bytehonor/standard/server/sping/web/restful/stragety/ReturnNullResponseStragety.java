package com.bytehonor.standard.server.sping.web.restful.stragety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import com.bytehonor.protocol.common.server.code.StandardCode;
import com.bytehonor.protocol.common.server.result.JsonResponse;
import com.bytehonor.standard.server.sping.config.SpringBootStandardProperties;

public final class ReturnNullResponseStragety implements ResponseStragety {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReturnNullResponseStragety.class);

	private final ServerHttpResponse response;

	private final boolean enableDebugRequest;
	
	private final SpringBootStandardProperties standardSpringBootProperties;

	public ReturnNullResponseStragety(ServerHttpResponse response, SpringBootStandardProperties standardSpringBootProperties) {
		this.response = response;
		this.standardSpringBootProperties = standardSpringBootProperties;
		this.enableDebugRequest = standardSpringBootProperties != null ? standardSpringBootProperties.isRestfulDebugEnable() : false;
	}

	@Override
	public JsonResponse<?> process(ServerHttpRequest request) {
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		jsonResponse.setCode(StandardCode.UNDEFINED_ERROR);
		String nullTIp = "METHOD_RETURN_NULL";
		
		if (enableDebugRequest) {
			jsonResponse.setMessage(buildDebugNullMessage(request, nullTIp));
		} else {
			jsonResponse.setMessage(nullTIp);
		}
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		if (response != null) {
			response.setStatusCode(httpStatus);
		}
		if (LOG.isWarnEnabled()) {
			LOG.warn("ErrorCode:{}, HttpStatus:{}", jsonResponse.getCode(), httpStatus.value());
		}
		return jsonResponse;
	}

	private String buildDebugNullMessage(ServerHttpRequest request, String nullTip) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getURI().getPath()).append(":").append(nullTip);
		return sb.toString();
	}

	public ServerHttpResponse getResponse() {
		return response;
	}

	public SpringBootStandardProperties getStandardSpringBootProperties() {
		return standardSpringBootProperties;
	}

	public boolean isEnableDebugRequest() {
		return enableDebugRequest;
	}

}
