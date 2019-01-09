package com.bytehonor.sdk.server.spring.web.error.stragety;

import com.bytehonor.sdk.server.spring.web.error.entity.ExceptionEntity;

public interface ExceptionStragety {
	
	ExceptionEntity process();

}
