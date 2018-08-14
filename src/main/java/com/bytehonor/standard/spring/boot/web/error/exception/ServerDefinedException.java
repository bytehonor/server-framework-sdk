package com.bytehonor.standard.spring.boot.web.error.exception;


import com.bytehonor.standard.api.protocol.code.StandardCode;
import com.bytehonor.standard.api.protocol.error.StandardException;

public class ServerDefinedException extends StandardException {

	private static final long serialVersionUID = 8241747723232910227L;
	
	public ServerDefinedException() {
		super();
		this.setCode(StandardCode.UNDEFINED_ERROR);
	}

	public ServerDefinedException(int code, String message) {
		super(code, message);
	}
}
