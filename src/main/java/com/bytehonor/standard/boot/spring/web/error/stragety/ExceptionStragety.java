package com.bytehonor.standard.boot.spring.web.error.stragety;

import com.bytehonor.standard.boot.spring.web.error.exception.entity.ExceptionEntity;

public interface ExceptionStragety {
	
	ExceptionEntity process();

}
