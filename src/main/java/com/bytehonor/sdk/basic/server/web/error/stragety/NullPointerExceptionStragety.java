package com.bytehonor.sdk.basic.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.basic.define.code.StandardCode;
import com.bytehonor.sdk.basic.server.web.error.ExceptionHolder;
import com.bytehonor.sdk.basic.server.web.error.ExceptionStragety;

public class NullPointerExceptionStragety implements ExceptionStragety {
	
	private NullPointerException exception;
	
	public NullPointerExceptionStragety(NullPointerException exception) {
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
