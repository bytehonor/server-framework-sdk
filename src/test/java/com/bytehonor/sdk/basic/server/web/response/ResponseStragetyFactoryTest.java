package com.bytehonor.sdk.basic.server.web.response;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bytehonor.sdk.basic.server.web.error.ExceptionHolder;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnErrorResponseStragety;
import com.bytehonor.sdk.basic.server.web.response.stragety.ReturnNullResponseStragety;

public class ResponseStragetyFactoryTest {

	@Test
	public void testBuild() {
		ResponseStragety s1 = ResponseStragetyFactory.build(null, null, null, null);

		boolean t1 = s1 instanceof ReturnNullResponseStragety;

		ResponseStragety s2 = ResponseStragetyFactory.build(new ExceptionHolder(), null, null, null);

		boolean t2 = s2 instanceof ReturnErrorResponseStragety;

		boolean isOk = t1 && t2;

		assertTrue(isOk, "testBuild");
	}

}
