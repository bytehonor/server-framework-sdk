package com.bytehonor.sdk.server.spring.query;

import java.util.List;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.server.spring.jdbc.SqlInjectUtils;
import com.bytehonor.sdk.server.spring.jdbc.SqlOperator;
import com.bytehonor.sdk.server.spring.string.StringCreator;
import com.bytehonor.sdk.server.spring.util.ListParamUtils;

public class MatchColumn {

    private String key;

    private Object value;

    private SqlOperator operator;

    public static MatchColumn eq(String key, String value) {
        if (StringUtils.isEmpty(value) == false) {
            value = SqlInjectUtils.escape(value);
        }
        return new MatchColumn(key, value, SqlOperator.EQ);
    }

    public static MatchColumn eq(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.EQ);
    }

    public static MatchColumn eq(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.EQ);
    }

    public static MatchColumn eq(String key, Boolean value) {
        return new MatchColumn(key, value, SqlOperator.EQ);
    }

    public static MatchColumn neq(String key, String value) {
        return new MatchColumn(key, value, SqlOperator.NEQ);
    }

    public static MatchColumn neq(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.NEQ);
    }

    public static MatchColumn neq(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.NEQ);
    }

    public static MatchColumn neq(String key, Boolean value) {
        return new MatchColumn(key, value, SqlOperator.NEQ);
    }

    public static MatchColumn gt(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.GT);
    }

    public static MatchColumn gt(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.GT);
    }

    public static MatchColumn egt(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.EGT);
    }

    public static MatchColumn egt(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.EGT);
    }

    public static MatchColumn lt(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.LT);
    }

    public static MatchColumn lt(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.LT);
    }

    public static MatchColumn elt(String key, Long value) {
        return new MatchColumn(key, value, SqlOperator.ELT);
    }

    public static MatchColumn elt(String key, Integer value) {
        return new MatchColumn(key, value, SqlOperator.ELT);
    }

    public static MatchColumn in(String key, List<String> value) {
        String src = null;
        if (value != null && value.isEmpty() == false) {
            src = StringCreator.create().append("(").append(ListParamUtils.joinString(value)).append(")").toString();
        }
        return new MatchColumn(key, src, SqlOperator.IN);
    }

    private MatchColumn(String key, Object value, SqlOperator operator) {
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
