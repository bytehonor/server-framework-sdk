package com.bytehonor.sdk.server.spring.exception;


import com.bytehonor.protocol.core.server.code.StandardCode;
import com.bytehonor.protocol.core.server.error.StandardException;

public class SpringServerException extends StandardException {

	private static final long serialVersionUID = 8241747723232910227L;
	
	public SpringServerException() {
		super();
		this.setCode(StandardCode.UNDEFINED_ERROR);
	}

	public SpringServerException(int code, String message) {
		super(code, message);
	}
}
