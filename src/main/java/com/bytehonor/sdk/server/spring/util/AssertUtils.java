package com.bytehonor.sdk.server.spring.util;

import com.bytehonor.sdk.server.spring.validate.IntegerValidate;
import com.bytehonor.sdk.server.spring.validate.LongValidate;
import com.bytehonor.sdk.server.spring.validate.StringValidate;

/**
 * @author lijianqiang
 *
 */
public class AssertUtils {

	/**
	 * @param src
	 * @param minLength
	 * @param maxLength
	 * @param message
	 */
	public static void checkLength(String src, int minLength, int maxLength, String message) {
		StringValidate.length(src, minLength, maxLength, message);
	}

	/**
	 * @param src
	 * @param minLength
	 * @param maxLength
	 * @param message
	 * @param isOptional
	 */
	public static void checkLength(String src, int minLength, int maxLength, String message, boolean isOptional) {
		if (isOptional && src == null) {
			return;
		}
		StringValidate.length(src, minLength, maxLength, message);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param message
	 */
	public static void checkInteger(Integer src, int min, int max, String message) {
		IntegerValidate.range(src, min, max, message);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param message
	 * @param isOptional
	 */
	public static void checkInteger(Integer src, int min, int max, String message, boolean isOptional) {
		if (isOptional && src == null) {
			return;
		}
		checkInteger(src, min, max, message);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param message
	 */
	public static void checkLong(Long src, long min, long max, String message) {
		LongValidate.range(src, min, max, message);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @param message
	 * @param isOptional
	 */
	public static void checkLong(Long src, long min, long max, String message, boolean isOptional) {
		if (isOptional && src == null) {
			return;
		}
		checkLong(src, min, max, message);
	}

}
