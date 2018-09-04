package com.bytehonor.standard.boot.spring.web.restful.helper;

import org.springframework.http.HttpHeaders;

import com.bytehonor.protocol.api.server.constant.ForceStatusHeader;

public class ResponseStragetyHelper {
	
	public static final boolean isForceHttpStatusOk(HttpHeaders headers) {
		String val = headers.getFirst(ForceStatusHeader.FORCE_STATUS_OK_KEY);
		return ForceStatusHeader.FORCE_STATUS_OK_VALUE.equals(val);
	}

}
