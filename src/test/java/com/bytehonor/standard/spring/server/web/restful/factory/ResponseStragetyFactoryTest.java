package com.bytehonor.standard.spring.server.web.restful.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bytehonor.standard.sping.server.web.error.exception.entity.ExceptionEntity;
import com.bytehonor.standard.sping.server.web.restful.factory.ResponseStragetyFactory;
import com.bytehonor.standard.sping.server.web.restful.stragety.ResponseStragety;
import com.bytehonor.standard.sping.server.web.restful.stragety.ReturnErrorResponseStragety;
import com.bytehonor.standard.sping.server.web.restful.stragety.ReturnNullResponseStragety;

public class ResponseStragetyFactoryTest {

	@Test
	public void testBuild() {
		ResponseStragety s1 = ResponseStragetyFactory.build(null, null, null, null);

		boolean t1 = s1 instanceof ReturnNullResponseStragety;

		ResponseStragety s2 = ResponseStragetyFactory.build(new ExceptionEntity(), null, null, null);

		boolean t2 = s2 instanceof ReturnErrorResponseStragety;

		boolean isOk = t1 && t2;

		assertTrue("testBuild", isOk);
	}

}
