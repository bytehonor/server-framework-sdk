package com.bytehonor.sdk.server.spring.getter;

import java.util.Objects;

import com.bytehonor.sdk.server.spring.validate.StringValidate;

public class StringGetter {

	public static String requireLength(String src, int min, int max, String message) {
		Objects.requireNonNull(src, message);
		return optionalLength(src, min, max, message);
	}

	public static String optionalLength(String src, int min, int max, String message) {
		if (src != null) {
			StringValidate.length(src, min, max, message);
		}

		return src;
	}

	public static String require(String src, String def) {
		return src != null ? src : def;
	}
}
