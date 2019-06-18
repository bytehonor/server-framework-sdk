package com.bytehonor.sdk.server.spring.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.protocol.common.code.StandardCode;
import com.bytehonor.sdk.server.spring.web.error.ExceptionHolder;
import com.bytehonor.sdk.server.spring.web.error.ExceptionStragety;

public class NullPointerExceptionStragety implements ExceptionStragety {
	
	private NullPointerException exception;
	
	public NullPointerExceptionStragety(NullPointerException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionHolder process() {
		ExceptionHolder error = new ExceptionHolder();
    	error.setStatus(HttpStatus.BAD_REQUEST.value());
    	error.setCode(StandardCode.UNDEFINED_ERROR);
    	error.setException(exception);
        return error;
	}

}
