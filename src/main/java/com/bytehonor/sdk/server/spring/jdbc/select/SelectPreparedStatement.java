package com.bytehonor.sdk.server.spring.jdbc.select;

import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.jdbc.SqlConstants;
import com.bytehonor.sdk.server.spring.query.QueryCondition;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class SelectPreparedStatement {

    private final String table;

    private final QueryCondition condition;

    private SelectPreparedStatement(String table, QueryCondition condition) {
        this.table = table;
        this.condition = condition;
    }

    public static SelectPreparedStatement create(String table, QueryCondition condition) {
        Objects.requireNonNull(table, "table");
        Objects.requireNonNull(condition, "condition");
        return new SelectPreparedStatement(table, condition);
    }

    public String getTable() {
        return table;
    }

    public QueryCondition getCondition() {
        return condition;
    }

    public String toSelectSql(String keys) {
        Objects.requireNonNull(keys, "keys");
        return StringCreator.create().append(SqlConstants.SELECT).append(keys).append(SqlConstants.FROM).append(table)
                .append(" WHERE 1=1 ").append(condition.conditionListSql()).toString();
    }

    public String toSelectCountSql(String key) {
        Objects.requireNonNull(key, "key");
        return StringCreator.create().append(SqlConstants.SELECT).append("count(").append(key).append(")")
                .append(SqlConstants.FROM).append(table).append(" WHERE 1=1 ").append(condition.conditionCountSql())
                .toString();
    }

    public List<Object> selectArgs() {
        return condition.conditionArgs();
    }

}
