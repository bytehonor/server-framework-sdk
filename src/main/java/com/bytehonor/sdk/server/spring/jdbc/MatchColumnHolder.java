package com.bytehonor.sdk.server.spring.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.server.spring.query.MatchColumn;

public class MatchColumnHolder {

    private StringBuilder sb;

    private List<Object> args;

    private MatchColumnHolder() {
        sb = new StringBuilder();
        args = new ArrayList<Object>();
    }

    public static MatchColumnHolder create() {
        return new MatchColumnHolder();
    }

    public MatchColumnHolder eq(String key, Object value) {
        this.append(key, value, SqlOperator.EQ);
        return this;
    }

    public MatchColumnHolder neq(String key, Object value) {
        this.append(key, value, SqlOperator.NEQ);
        return this;
    }

    public MatchColumnHolder gt(String key, Object value) {
        this.append(key, value, SqlOperator.GT);
        return this;
    }

    public MatchColumnHolder egt(String key, Object value) {
        this.append(key, value, SqlOperator.EGT);
        return this;
    }

    public MatchColumnHolder lt(String key, Object value) {
        this.append(key, value, SqlOperator.LT);
        return this;
    }

    public MatchColumnHolder elt(String key, Object value) {
        this.append(key, value, SqlOperator.ELT);
        return this;
    }

    public MatchColumnHolder between(String key, Object begin, Object end) {
        if (StringUtils.isEmpty(key) || begin == null || end == null) {
            return this;
        }
        this.sb.append(SqlConstants.AND).append(key).append(SqlConstants.BLANK).append(SqlOperator.BETWEEN)
                .append(SqlConstants.BLANK).append(SqlConstants.PARA).append(SqlConstants.AND)
                .append(SqlConstants.PARA);
        this.args.add(begin);
        this.args.add(end);
        return this;
    }

    public MatchColumnHolder append(String key, Object value, SqlOperator operator) {
        this.and(new MatchColumn(key, value, operator));
        return this;
    }

    /**
     * uncheck inject
     * 
     * @param column
     * @return
     */
    public MatchColumnHolder and(MatchColumn column) {
        Objects.requireNonNull(column, "column");
        Objects.requireNonNull(column.getOperator(), "operator");
        if (StringUtils.isEmpty(column.getKey()) || column.getValue() == null) {
            return this;
        }
        // TODO value inject filter
        this.sb.append(SqlConstants.AND).append(column.getKey()).append(SqlConstants.BLANK)
                .append(column.getOperator().getOpt()).append(SqlConstants.BLANK).append(SqlConstants.PARA);
        this.args.add(column.getValue());
        return this;
    }

    @Override
    public String toString() {
        return toAndSql();
    }

    public String toAndSql() {
        return sb.toString();
    }

    public List<Object> getArgs() {
        return args;
    }

}
