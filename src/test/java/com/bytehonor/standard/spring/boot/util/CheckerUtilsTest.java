package com.bytehonor.standard.spring.boot.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bytehonor.standard.spring.boot.util.CheckerUtils;

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

	@Test
	public void testCheckLength() {
		boolean t1 = CheckerUtils.checkLength("abcd", 1, 3) == false;
		boolean t2 = CheckerUtils.checkLength("abcd", 6, 9) == false;
		boolean t3 = CheckerUtils.checkLength("", 6, 9) == false;
		boolean t4 = CheckerUtils.checkLength(null, 0, 9) == false;
		boolean t5 = CheckerUtils.checkLength("", 0, 3);
		boolean t6 = CheckerUtils.checkLength("1", 1, 3);
		boolean isOk = t1 & t2 && t3 && t4 && t5 && t6;
		assertTrue("testCheckLength", isOk);
	}

	@Test
	public void testCheckIntege() {
		boolean t1 = CheckerUtils.checkInteger(1, 2, 10) == false;
		boolean t2 = CheckerUtils.checkInteger(11, 1, 10) == false;
		boolean t3 = CheckerUtils.checkInteger(null, 1, 10) == false;
		boolean t4 = CheckerUtils.checkInteger(1, 1, 10);
		boolean t5 = CheckerUtils.checkInteger(10, 1, 10);
		boolean t6 = CheckerUtils.checkInteger(5, 1, 10);
		boolean isOk = t1 & t2 && t3 && t4 && t5 && t6;
		assertTrue("testCheckIntege", isOk);
	}

	@Test
	public void testCheckLong() {
		boolean t1 = CheckerUtils.checkLong(1L, 2L, 10L) == false;
		boolean t2 = CheckerUtils.checkLong(11L, 1L, 10L) == false;
		boolean t3 = CheckerUtils.checkLong(null, 1L, 10L) == false;
		boolean t4 = CheckerUtils.checkLong(1L, 1L, 10000000L);
		boolean t5 = CheckerUtils.checkLong(10000000L, 1L, 10000000L);
		boolean t6 = CheckerUtils.checkLong(5L, 1L, 10L);
		boolean isOk = t1 & t2 && t3 && t4 && t5 && t6;
		assertTrue("testCheckLong", isOk);
	}

}
