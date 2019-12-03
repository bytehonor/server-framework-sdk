package com.bytehonor.sdk.basic.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.basic.server.web.error.ExceptionHolder;
import com.bytehonor.sdk.basic.server.web.error.ExceptionStragety;
import com.bytehonor.sdk.protocol.common.code.StandardCode;

public class DefaultExceptionStragety implements ExceptionStragety {

	private Exception exception;

	public DefaultExceptionStragety(Exception exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionHolder hold() {
		ExceptionHolder error = new ExceptionHolder();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setCode(StandardCode.UNDEFINED_ERROR);
		error.setException(exception);
		return error;
	}

}
