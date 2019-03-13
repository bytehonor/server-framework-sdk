package com.bytehonor.sdk.server.spring.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.web.error.entity.ExceptionEntity;

public class ServerDefinedExceptionStragety implements ExceptionStragety {
	
	private SpringServerException exception;
	
	public ServerDefinedExceptionStragety(SpringServerException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionEntity process() {
		ExceptionEntity error = new ExceptionEntity();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setCode(exception.getCode());
    	error.setException(exception);
        return error;
	}

}
