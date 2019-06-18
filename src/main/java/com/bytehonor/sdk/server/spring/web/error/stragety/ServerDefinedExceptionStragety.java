package com.bytehonor.sdk.server.spring.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.web.error.ExceptionHolder;
import com.bytehonor.sdk.server.spring.web.error.ExceptionStragety;

public class ServerDefinedExceptionStragety implements ExceptionStragety {
	
	private SpringServerException exception;
	
	public ServerDefinedExceptionStragety(SpringServerException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionHolder process() {
		ExceptionHolder error = new ExceptionHolder();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setCode(exception.getCode());
    	error.setException(exception);
        return error;
	}

}
