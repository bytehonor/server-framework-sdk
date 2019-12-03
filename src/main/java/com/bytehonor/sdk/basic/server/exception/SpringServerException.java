package com.bytehonor.sdk.basic.server.exception;


import com.bytehonor.sdk.protocol.common.code.StandardCode;
import com.bytehonor.sdk.protocol.common.error.StandardException;

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
