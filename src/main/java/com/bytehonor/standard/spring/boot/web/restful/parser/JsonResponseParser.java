package com.bytehonor.standard.spring.boot.web.restful.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.standard.api.protocol.code.StandardCode;
import com.bytehonor.standard.api.protocol.result.JsonResponse;
import com.bytehonor.standard.spring.boot.web.error.exception.InternalRestfulException;

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
