package com.bytehonor.standard.server.sping.util;

public class SafeGetter {

	public static String getStr(String val, String def) {
		return isEmpty(val) == false ? val : def;
	}

	public static Integer getInt(Integer val, Integer def) {
		return val != null ? val : def;
	}

	public static Long getLong(Long val, Long def) {
		return val != null ? val : def;
	}
	
	public static Boolean getBoolean(Boolean val, Boolean def) {
		return val != null ? val : def;
	}

	public static boolean isEmpty(String val) {
		return (val == null || "".equals(val));
	}

}
