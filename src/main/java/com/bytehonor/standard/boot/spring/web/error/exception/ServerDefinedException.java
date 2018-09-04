package com.bytehonor.standard.boot.spring.web.error.exception;


import com.bytehonor.protocol.api.server.code.StandardCode;
import com.bytehonor.protocol.api.server.error.StandardException;

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
