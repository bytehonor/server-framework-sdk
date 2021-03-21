package com.bytehonor.sdk.basic.server.validate;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IntegerValidateTest {

	@Test
	public void testRange() {
		boolean isOk = true;
		try {
			IntegerValidate.range(2, 1, 3, "key");
		} catch (Exception e) {
			isOk = false;
		}
		assertTrue(isOk, "testCheckLength");
	}

}
