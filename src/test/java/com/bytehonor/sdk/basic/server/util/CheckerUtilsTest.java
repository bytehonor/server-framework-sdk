package com.bytehonor.sdk.basic.server.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckerUtilsTest {

	@Test
	public void testCheckMobile() {
		boolean t1 = CheckerUtils.isMobile("123") == false;
		boolean t2 = CheckerUtils.isMobile("13305901084x") == false;
		boolean t3 = CheckerUtils.isMobile("1330590108x") == false;
		boolean t4 = CheckerUtils.isMobile("abcdefabcdfrg") == false;
		boolean t5 = CheckerUtils.isMobile("") == false;
		boolean t6 = CheckerUtils.isMobile(null) == false;
		boolean t7 = CheckerUtils.isMobile("13805901234");
		boolean isOk = t1 & t2 && t3 && t4 && t5 && t6 && t7;
		assertTrue("testCheckMobile", isOk);
	}

}
