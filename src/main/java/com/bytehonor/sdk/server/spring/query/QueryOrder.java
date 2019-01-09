package com.bytehonor.sdk.server.spring.query;

public class QueryOrder {

	public static final String ASC = "ASC";

	public static final String DESC = "DESC";

	public static final String BLANK = " ";

	private String column;

	private boolean asc;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String toSql() {
		StringBuilder sb = new StringBuilder(BLANK).append(column).append(BLANK);
		if (asc) {
			sb.append(ASC);
		} else {
			sb.append(DESC);
		}
		sb.append(BLANK);
		return sb.toString();
	}

	@Override
	public String toString() {
		return toSql();
	}
}
