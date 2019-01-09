package com.bytehonor.sdk.server.spring.validate;

import java.util.Objects;

import com.bytehonor.sdk.server.spring.exception.ParameterExcption;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class LongValidate {

	public static void range(Long src, long min, long max, String message) {
		Objects.requireNonNull(src, message);
		if (src.longValue() < min) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" < ").append(min).toString());
		}
		if (src.longValue() > max) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" > ").append(max).toString());
		}
	}
}
