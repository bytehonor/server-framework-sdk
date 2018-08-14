package com.bytehonor.standard.spring.boot.web.error.stragety;

import com.bytehonor.standard.spring.boot.web.error.exception.entity.ExceptionEntity;

public interface ExceptionStragety {
	
	ExceptionEntity process();

}
