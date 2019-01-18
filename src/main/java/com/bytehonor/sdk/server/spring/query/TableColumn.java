package com.bytehonor.sdk.server.spring.query;

public class TableColumn {

    private String key;

    private Object value;

    private SqlOperator operator;

    public static TableColumn eq(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.EQ);
    }

    public static TableColumn neq(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.NEQ);
    }

    public static TableColumn gt(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.GT);
    }

    public static TableColumn egt(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.EGT);
    }

    public static TableColumn lt(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.LT);
    }

    public static TableColumn elt(String key, Object value) {
        return new TableColumn(key, value, SqlOperator.ELT);
    }

    public TableColumn() {
        this(null, null, null);
    }

    public TableColumn(String key, Object value) {
        this(key, value, SqlOperator.EQ);
    }

    public TableColumn(String key, Object value, SqlOperator operator) {
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
