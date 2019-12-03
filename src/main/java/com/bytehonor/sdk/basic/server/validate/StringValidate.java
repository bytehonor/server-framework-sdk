package com.bytehonor.sdk.basic.server.validate;

import java.util.Objects;

import com.bytehonor.sdk.basic.lang.string.StringCreator;
import com.bytehonor.sdk.basic.server.exception.ParameterExcption;

public class StringValidate {

	public static void length(String src, int min, int max, String message) {
		Objects.requireNonNull(src, message);
		if (src.length() < min) {
			throw new ParameterExcption(
					StringCreator.create().append(message).append(" length < ").append(min).toString());
		}
		if (src.length() > max) {
			throw new ParameterExcption(
					StringCreator.create().append(message).append(" length > ").append(max).toString());
		}
	}
}
