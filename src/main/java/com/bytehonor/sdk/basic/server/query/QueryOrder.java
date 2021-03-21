package com.bytehonor.sdk.basic.server.query;

import java.util.Objects;

import com.bytehonor.sdk.basic.server.jdbc.SqlConstants;
import com.bytehonor.sdk.basic.server.jdbc.SqlInjectUtils;
import com.bytehonor.sdk.basic.server.util.StringObject;

public class QueryOrder {

    private String column;

    private boolean desc;

    public static QueryOrder descOf(String column) {
        Objects.requireNonNull(column, "column");
        return new QueryOrder(column, true);
    }

    public static QueryOrder ascOf(String column) {
        Objects.requireNonNull(column, "column");
        return new QueryOrder(column, false);
    }
    
    public static QueryOrder of(String column, boolean desc) {
        Objects.requireNonNull(column, "column");
        return new QueryOrder(column, desc);
    }

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
        if (StringObject.isEmpty(column)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" ORDER BY ").append(SqlInjectUtils.column(column))
                .append(SqlConstants.BLANK);
        if (desc) {
            sb.append(SqlConstants.DESC);
        } else {
            sb.append(SqlConstants.ASC);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toSql();
    }
}
