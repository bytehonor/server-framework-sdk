package com.bytehonor.sdk.basic.server.getter;

import java.util.Set;

import com.google.common.collect.Sets;

public class BooleanGetter {

	private static final Set<String> YES = Sets.newHashSet("true", "yes");

	public static Boolean require(String src) {
		return require(src, false);
	}

	public static Boolean require(String src, Boolean defVal) {
		if (src == null) {
			return defVal;
		}
		return YES.contains(src.toLowerCase());
	}
	
	public static Boolean require(Boolean src, Boolean def) {
		return src != null ? src : def;
	}
}
