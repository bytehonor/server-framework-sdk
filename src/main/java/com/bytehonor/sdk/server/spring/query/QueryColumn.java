package com.bytehonor.sdk.server.spring.query;

import com.bytehonor.sdk.server.spring.jdbc.SqlOperator;

public class QueryColumn {

    private String key;

    private Object value;

    private SqlOperator operator;

    public static QueryColumn eq(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.EQ);
    }

    public static QueryColumn neq(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.NEQ);
    }

    public static QueryColumn gt(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.GT);
    }

    public static QueryColumn egt(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.EGT);
    }

    public static QueryColumn lt(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.LT);
    }

    public static QueryColumn elt(String key, Object value) {
        return new QueryColumn(key, value, SqlOperator.ELT);
    }

    public QueryColumn() {
        this(null, null, null);
    }

    public QueryColumn(String key, Object value) {
        this(key, value, SqlOperator.EQ);
    }

    public QueryColumn(String key, Object value, SqlOperator operator) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SqlOperator getOperator() {
        return operator;
    }

    public void setOperator(SqlOperator operator) {
        this.operator = operator;
    }

}
