package com.bytehonor.standard.sping.server.util;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.standard.sping.server.constant.HttpConstant;
import com.bytehonor.standard.sping.server.web.error.exception.ServerDefinedException;

/**
 * @author lijianqiang
 *
 */
public class HttpRequestUtils {

	/**
	 * @param request
	 * @return
	 */
	public static boolean isCount(HttpServletRequest request) {
		String val = request.getParameter(HttpConstant.COUNT_KEY);
		if (SafeGetter.isEmpty(val)) {
			return false;
		}

		return HttpConstant.COUNT_YES.equals(val);
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getLimit(HttpServletRequest request) {
		String val = request.getParameter(HttpConstant.LIMIT_KEY);

		int res = 20;
		if (SafeGetter.isEmpty(val)) {
			return res;
		}
		try {
			res = Integer.valueOf(val);
		} catch (Exception e) {

		}
		if (res > HttpConstant.LIMIT_MAX) {
			res = HttpConstant.LIMIT_MAX;
		}
		return res;
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getOffset(HttpServletRequest request) {
		String val = request.getParameter(HttpConstant.OFFSET_KEY);

		int res = 0;
		if (SafeGetter.isEmpty(val)) {
			return res;
		}
		try {
			res = Integer.valueOf(val);
		} catch (Exception e) {

		}
		return res;
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Integer getInteger(HttpServletRequest request, String key) {
		String val = getValue(request, key);
		try {
			return Integer.valueOf(val);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String key) {
		String val = getValue(request, key);
		try {
			return Long.valueOf(val);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String key) {
		String val = getValue(request, key);
		if (SafeGetter.isEmpty(val)) {
			return null;
		}

		return "true".equals(val);
	}

	private static String getValue(HttpServletRequest request, String key) {
		if (SafeGetter.isEmpty(key)) {
			throw new ServerDefinedException(1000, "key is empty");
		}
		return request.getParameter(key);
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key) {
		return getValue(request, key);

	}

}
