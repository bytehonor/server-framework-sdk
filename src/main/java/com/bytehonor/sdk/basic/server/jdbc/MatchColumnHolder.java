package com.bytehonor.sdk.basic.server.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.server.query.MatchColumn;

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

    /**
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
        if (SqlOperator.IN.getKey().equals(column.getOperator().getKey())) {
            this.sb.append(SqlConstants.AND).append(column.getKey()).append(SqlConstants.BLANK)
                    .append(column.getOperator().getOpt()).append(SqlConstants.BLANK).append(column.getValue());
            return this;
        }
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
