package com.bytehonor.sdk.server.spring.query;

import com.bytehonor.sdk.server.spring.jdbc.SqlOperator;

public class MatchColumn {

    private String key;

    private Object value;

    private SqlOperator operator;

    public static MatchColumn eq(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.EQ);
    }

    public static MatchColumn neq(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.NEQ);
    }

    public static MatchColumn gt(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.GT);
    }

    public static MatchColumn egt(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.EGT);
    }

    public static MatchColumn lt(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.LT);
    }

    public static MatchColumn elt(String key, Object value) {
        return new MatchColumn(key, value, SqlOperator.ELT);
    }

    public MatchColumn() {
        this(null, null, null);
    }

    public MatchColumn(String key, Object value) {
        this(key, value, SqlOperator.EQ);
    }

    public MatchColumn(String key, Object value, SqlOperator operator) {
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
