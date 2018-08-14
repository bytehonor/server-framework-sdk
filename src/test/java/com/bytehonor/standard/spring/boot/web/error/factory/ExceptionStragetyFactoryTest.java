package com.bytehonor.standard.spring.boot.web.error.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bytehonor.standard.spring.boot.web.error.exception.InternalRestfulException;
import com.bytehonor.standard.spring.boot.web.error.exception.ServerDefinedException;
import com.bytehonor.standard.spring.boot.web.error.factory.ExceptionStragetyFactory;
import com.bytehonor.standard.spring.boot.web.error.stragety.ExceptionStragety;
import com.bytehonor.standard.spring.boot.web.error.stragety.InternalRestfulExceptionStragety;
import com.bytehonor.standard.spring.boot.web.error.stragety.NullPointerExceptionStragety;
import com.bytehonor.standard.spring.boot.web.error.stragety.ServerDefinedExceptionStragety;

public class ExceptionStragetyFactoryTest {

	@Test
	public void testBuild() {

		ExceptionStragety s1 = ExceptionStragetyFactory.build(new NullPointerException());
		boolean t1 = s1 instanceof NullPointerExceptionStragety;

		ExceptionStragety s2 = ExceptionStragetyFactory.build(new InternalRestfulException());
		boolean t2 = s2 instanceof InternalRestfulExceptionStragety;

		ExceptionStragety s3 = ExceptionStragetyFactory.build(new ServerDefinedException());
		boolean t3 = s3 instanceof ServerDefinedExceptionStragety;

		boolean isOk = t1 && t2 && t3;
		assertTrue("testBuild", isOk);
	}

}
