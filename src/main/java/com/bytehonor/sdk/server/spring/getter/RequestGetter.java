package com.bytehonor.sdk.server.spring.getter;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.protocol.common.constant.HttpConstants;

/**
 * @author lijianqiang
 *
 */
public class RequestGetter {

	/**
	 * @param request
	 * @return
	 */
	public static boolean isCount(HttpServletRequest request) {
		return BooleanGetter.require(request.getParameter(HttpConstants.COUNT_KEY));
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getLimit(HttpServletRequest request) {
		int res = IntegerGetter.require(request.getParameter(HttpConstants.LIMIT_KEY), HttpConstants.LIMIT_DEF);
		if (res > HttpConstants.LIMIT_MAX) {
			res = HttpConstants.LIMIT_MAX;
		}
		return res;
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getOffset(HttpServletRequest request) {
		return IntegerGetter.require(request.getParameter(HttpConstants.OFFSET_KEY), HttpConstants.OFFSET_DEFAULT);
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Integer getInteger(HttpServletRequest request, String key) {
		return IntegerGetter.require(getValue(request, key), null);
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String key) {
		return LongGetter.require(getValue(request, key), null);
	}

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String key) {
		return BooleanGetter.require(getValue(request, key), null);
	}

	private static String getValue(HttpServletRequest request, String key) {
		Objects.requireNonNull(request, "request");
		Objects.requireNonNull(key, "key");
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
