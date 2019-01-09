package com.bytehonor.sdk.server.spring.util;

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
}
