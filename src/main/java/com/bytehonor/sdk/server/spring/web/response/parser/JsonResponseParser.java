package com.bytehonor.sdk.server.spring.web.response.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.protocol.core.server.code.StandardCode;
import com.bytehonor.protocol.core.server.result.JsonResponse;
import com.bytehonor.sdk.server.spring.exception.InternalRestfulException;

public class JsonResponseParser {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonResponseParser.class);
	
	public static <T> T safeGet(JsonResponse<T> response) {
		if (response == null) {
			throw new InternalRestfulException(StandardCode.INTERNAL_ERROR, "RESPONSE NULL");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("JsonResponse ErrorCode:{}", response.getCode());
		}
		if (response.getCode() != StandardCode.OK) {
			throw new InternalRestfulException(response.getCode(), response.getMessage());
		}
		T data = response.getData();
		if (data == null) {
			throw new InternalRestfulException(StandardCode.INTERNAL_ERROR, "RESPONSE BODY NULL");
		}
		return data;
	}

}
