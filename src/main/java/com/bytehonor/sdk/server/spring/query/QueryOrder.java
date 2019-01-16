package com.bytehonor.sdk.server.spring.query;

import org.springframework.util.StringUtils;

public class QueryOrder {

    public static final String DESC = "DESC";

    public static final String ASC = "ASC";

    public static final String BLANK = " ";

    private String column;

    private boolean desc;

    public QueryOrder() {
        this("id", false);
    }

    public QueryOrder(String column, boolean desc) {
        this.column = column;
        this.desc = desc;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public String toSql() {
        if (StringUtils.isEmpty(column)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" ORDER BY ").append(column).append(BLANK);
        if (desc) {
            sb.append(DESC);
        } else {
            sb.append(ASC);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toSql();
    }
}
