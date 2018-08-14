package com.bytehonor.standard.spring.boot.util;

import com.bytehonor.standard.spring.boot.web.error.exception.ServerDefinedException;

/**
 * @author lijianqiang
 *
 */
public class AssertUtils {

	/**
	 * @param src
	 * @param minLength
	 * @param maxLength
	 * @param keyName
	 */
	public static void checkLength(String src, int minLength, int maxLength, String keyName) {
		if (src == null) {
			throw new ServerDefinedException(1003, "请求参数不能为空" + keyName);
		}
		if (CheckerUtils.checkLength(src, minLength, maxLength) == false) {
			throw new ServerDefinedException(1002, "请求参数不合法" + keyName);
		}
	}
	
	/**
	 * @param src
	 * @param minLength
	 * @param maxLength
	 * @param keyName
	 * @param isOptional
	 */
	public static void checkLength(String src, int minLength, int maxLength, String keyName, boolean isOptional) {
		if (src == null && isOptional) {
			return;
		}
		checkLength(src, minLength, maxLength, keyName);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param keyName
	 */
	public static void checkInteger(Integer src, int min, int max, String keyName) {
		if (src == null) {
			throw new ServerDefinedException(1003, "请求参数不能为空" + keyName);
		}
		if (CheckerUtils.checkInteger(src, min, min) == false) {
			throw new ServerDefinedException(1002, "请求参数不合法" + keyName);
		}
	}
	
	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param keyName
	 * @param isOptional
	 */
	public static void checkInteger(Integer src, int min, int max, String keyName, boolean isOptional) {
		if (src == null && isOptional) {
			return;
		}
		checkInteger(src, min, max, keyName);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param keyName
	 */
	public static void checkLong(Long src, long min, long max, String keyName) {
		if (src == null) {
			throw new ServerDefinedException(1003, "请求参数不能为空" + keyName);
		}
		if (CheckerUtils.checkLong(src, min, min) == false) {
			throw new ServerDefinedException(1002, "请求参数不合法" + keyName);
		}
	}
	
	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param keyName
	 * @param isOptional
	 */
	public static void checkLong(Long src, long min, long max, String keyName, boolean isOptional) {
		if (src == null && isOptional) {
			return;
		}
		checkLong(src, min, max, keyName);
	}

}
