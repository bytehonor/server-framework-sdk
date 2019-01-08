package com.bytehonor.sdk.server.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.util.DoubleMathUtils;

public class DoubleMathUtilsTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(DoubleMathUtilsTest.class);
	
	private static Double DOU1 = 7.239;
	
	private static Double DOU2 = 6.265;
	
	@Test
	public void testAdd() {
		Double res = DoubleMathUtils.add(DOU1, DOU2);
		LOG.info("testAdd {} + {} = {}", DOU1, DOU2, res);
		boolean isOk = true;
		assertTrue("*testAdd*", isOk);
	}

	@Test
	public void testSub() {
		Double res = DoubleMathUtils.sub(DOU1, DOU2);
		LOG.info("testSub {} - {} = {}", DOU1, DOU2, res);
		boolean isOk = true;
		assertTrue("*testSub*", isOk);
	}

	@Test
	public void testMul() {
		Double res = DoubleMathUtils.mul(DOU1, DOU2);
		LOG.info("testMul {} * {} = {}", DOU1, DOU2, res);
		boolean isOk = true;
		assertTrue("*testMul*", isOk);
	}

	@Test
	public void testDivideDoubleDouble() {
		Double res = DoubleMathUtils.divide(DOU1, DOU2);
		LOG.info("testDivideDoubleDouble {} / {} = {}", DOU1, DOU2, res);
		boolean isOk = true;
		assertTrue("*testDivideDoubleDouble*", isOk);
	}

	@Test
	public void testDivideDoubleDoubleInteger() {
		Double res = DoubleMathUtils.divide(DOU1, DOU2, 2);
		LOG.info("testDivideDoubleDoubleInteger {} / {} = {}", DOU1, DOU2, res);
		boolean isOk = true;
		assertTrue("*testDivideDoubleDoubleInteger*", isOk);
	}

	@Test
	public void testRound() {
		Double res = DoubleMathUtils.round(DOU1, 1);
		LOG.info("testRound {} :{}", DOU1, res);
		boolean isOk = true;
		assertTrue("*testRound*", isOk);
	}

}
