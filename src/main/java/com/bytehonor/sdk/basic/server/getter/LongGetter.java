package com.bytehonor.sdk.basic.server.getter;

import java.util.Objects;

import com.bytehonor.sdk.basic.lang.string.StringCreator;
import com.bytehonor.sdk.basic.server.exception.ParameterExcption;
import com.bytehonor.sdk.basic.server.validate.LongValidate;

public class LongGetter {

	public static Long requireRange(Long src, long min, long max, String message) {
		Objects.requireNonNull(src, message);
		return optionalRange(src, min, max, message);
	}

	public static Long optionalRange(Long src, long min, long max, String message) {
		if (src != null) {
			LongValidate.range(src, min, max, message);
		}
		return src;
	}

	public static Long requireRange(String src, long min, long max, String message) {
		Objects.requireNonNull(src, message);
		return optionalRange(src, min, max, message);
	}

	public static Long optionalRange(String src, long min, long max, String message) {
		if (src == null) {
			return null;
		}
		return optionalRange(parse(src), min, max, message);
	}

	public static Long parse(String src) {
		try {
			return Long.valueOf(src);
		} catch (Exception e) {
			throw new ParameterExcption(StringCreator.create().append(src).append(" is not number ").toString());
		}
	}

	public static Long require(String src, Long def) {
	    if (src == null) {
            return def;
        }
		try {
			return parse(src);
		} catch (Exception e) {
			return def;
		}
	}
	
	public static Long require(Long val, Long def) {
		return val != null ? val : def;
	}
}
