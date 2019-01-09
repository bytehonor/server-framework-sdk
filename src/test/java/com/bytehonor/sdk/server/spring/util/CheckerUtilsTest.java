package com.bytehonor.sdk.server.spring.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bytehonor.sdk.server.spring.util.CheckerUtils;

public class CheckerUtilsTest {

	@Test
	public void testCheckMobile() {
		boolean t1 = CheckerUtils.checkMobile("123") == false;
		boolean t2 = CheckerUtils.checkMobile("13305901084x") == false;
		boolean t3 = CheckerUtils.checkMobile("1330590108x") == false;
		boolean t4 = CheckerUtils.checkMobile("abcdefabcdfrg") == false;
		boolean t5 = CheckerUtils.checkMobile("") == false;
		boolean t6 = CheckerUtils.checkMobile(null) == false;
		boolean t7 = CheckerUtils.checkMobile("13805901234");
		boolean isOk = t1 & t2 && t3 && t4 && t5 && t6 && t7;
		assertTrue("testCheckMobile", isOk);
	}

}
