package com.bytehonor.sdk.server.spring.jdbc.select;

import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.jdbc.SqlConstants;
import com.bytehonor.sdk.server.spring.query.QueryCondition;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class SelectPreparedStatement {

    private final String table;

    private final String primary;

    private final String columns;

    private final QueryCondition condition;

    private SelectPreparedStatement(String table, String primary, String columns, QueryCondition condition) {
        this.table = table;
        this.primary = primary;
        this.columns = columns;
        this.condition = condition;
    }

    /**
     * @param table
     * @param primary
     * @param columns
     * @param condition
     * @return
     */
    public static SelectPreparedStatement create(String table, String primary, String columns,
            QueryCondition condition) {
        Objects.requireNonNull(table, "table");
        Objects.requireNonNull(primary, "primary");
        Objects.requireNonNull(condition, "condition");
        return new SelectPreparedStatement(table, primary, columns, condition);
    }

    public String getTable() {
        return table;
    }

    public QueryCondition getCondition() {
        return condition;
    }

    public String getPrimary() {
        return primary;
    }

    public String getColumns() {
        return columns;
    }

    public String toSelectSql() {
        StringCreator creator = StringCreator.create();
        creator.append("SELECT ").append(columns).append(" FROM ").append(table).append(SqlConstants.BLANK);
        creator.append("WHERE 1=1").append(condition.getMatchHolder().toAndSql());
        if (condition.getOrder() != null) {
            creator.append(condition.getOrder().toSql());
        }
        creator.append(condition.offsetLimitSql());
        return creator.toString();
    }

    public String toCountSql() {
        StringCreator creator = StringCreator.create();
        creator.append("SELECT count(").append(primary).append(") FROM ").append(table).append(SqlConstants.BLANK);
        creator.append("WHERE 1=1").append(condition.getMatchHolder().toAndSql());
        return creator.toString();
    }

    public List<Object> args() {
        return condition.getMatchHolder().getArgs();
    }

}
