package com.bytehonor.standard.server.sping.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.bytehonor.protocol.common.server.code.StandardCode;
import com.bytehonor.standard.server.sping.web.error.exception.entity.ExceptionEntity;

public class NullPointerExceptionStragety implements ExceptionStragety {
	
	private NullPointerException exception;
	
	public NullPointerExceptionStragety(NullPointerException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionEntity process() {
		ExceptionEntity error = new ExceptionEntity();
    	error.setStatus(HttpStatus.BAD_REQUEST.value());
    	error.setCode(StandardCode.UNDEFINED_ERROR);
    	error.setException(exception);
        return error;
	}

}
