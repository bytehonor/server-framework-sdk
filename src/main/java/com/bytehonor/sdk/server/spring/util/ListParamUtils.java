package com.bytehonor.sdk.server.spring.util;

import java.util.List;

public class ListParamUtils {
	
	private static final String CON = ",";
	
	public static String joinString(List<String> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("'0'");
		for (String val : list) {
			sb.append(CON);
			sb.append("'").append(val).append("'");
		}
		return sb.toString();
	}
	
	public static String joinLong(List<Long> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("0");
		for (Long val : list) {
			sb.append(CON);
			sb.append(val);
		}
		return sb.toString();
	}
	
	public static String joinInteger(List<Integer> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("0");
		for (Integer val : list) {
			sb.append(CON);
			sb.append(val);
		}
		return sb.toString();
	}

}
