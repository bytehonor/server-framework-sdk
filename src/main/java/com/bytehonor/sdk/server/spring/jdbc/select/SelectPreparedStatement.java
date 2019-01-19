package com.bytehonor.sdk.server.spring.jdbc.select;

import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.exception.ServerDefinedException;
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
        StringCreator creator = StringCreator.create();
        creator.append(SqlConstants.SELECT).append(keys).append(SqlConstants.FROM).append(table);
        creator.append(" WHERE 1=1").append(condition.getMatchHolder().toAndSql());
        if (condition.getOrder() != null) {
            creator.append(condition.getOrder().toSql());
        }
        creator.append(condition.offsetLimitSql());
        return creator.toString();
    }

    public String toCountSql(String key) {
        Objects.requireNonNull(key, "key");
        StringCreator creator = StringCreator.create();
        creator.append(SqlConstants.SELECT).append("count(").append(key).append(")").append(SqlConstants.FROM)
                .append(table);
        creator.append(" WHERE 1=1").append(condition.getMatchHolder().toAndSql());
        return creator.toString();
    }

    public List<Object> args() {
        if (condition.getMatchHolder().getArgs().isEmpty()) {
            throw new ServerDefinedException(44, "select but without any match condition");
        }
        return condition.getMatchHolder().getArgs();
    }

}
