package com.bytehonor.standard.spring.boot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author lijianqiang
 *
 */
public class CheckerUtils {

	private static final String REGX = "^1[3|4|5|7|8][0-9]\\d{4,8}$";

	/**
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if (StringUtils.isEmpty(mobile) || mobile.length() < 11) {
			return false;
		}
		Pattern p = Pattern.compile(REGX);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * @param src
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean checkLength(String src, int minLength, int maxLength) {
		if (src == null) {
			return false;
		}
		int len = src.trim().length();
		return (len >= minLength && len <= maxLength);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkInteger(Integer src, int min, int max) {
		if (src == null) {
			return false;
		}
		int iv = src.intValue();
		return (iv >= min && iv <= max);
	}

	/**
	 * @param src
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkLong(Long src, long min, long max) {
		if (src == null) {
			return false;
		}
		long lv = src.longValue();
		return (lv >= min && lv <= max);
	}

}
