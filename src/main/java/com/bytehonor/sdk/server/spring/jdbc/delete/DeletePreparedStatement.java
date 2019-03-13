package com.bytehonor.sdk.server.spring.jdbc.delete;

import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.jdbc.MatchColumnHolder;
import com.bytehonor.sdk.server.spring.jdbc.SqlConstants;
import com.bytehonor.sdk.server.spring.query.MatchColumn;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class DeletePreparedStatement {

    private final String table;

    private final MatchColumnHolder matchHolder;

    private DeletePreparedStatement(String table) {
        this.table = table;
        this.matchHolder = MatchColumnHolder.create();
    }

    public static DeletePreparedStatement create(String table) {
        Objects.requireNonNull(table, "table");
        return new DeletePreparedStatement(table);
    }

    public DeletePreparedStatement match(MatchColumn column) {
        this.matchHolder.and(column);
        return this;
    }

    public String getTable() {
        return table;
    }

    public String toDeleteRealSql() {
        return StringCreator.create().append("DELETE FROM ").append(table).append(" WHERE 1=1")
                .append(matchHolder.toAndSql()).toString();
    }

    public String toDeleteLogicSql() {
        return StringCreator.create().append(SqlConstants.UPDATE).append(table).append(" SET deleted = 1 WHERE 1=1")
                .append(matchHolder.toAndSql()).toString();
    }

    public List<Object> args() {
        if (matchHolder.getArgs().isEmpty()) {
            throw new SpringServerException(44, "delete but without any match condition");
        }
        return matchHolder.getArgs();
    }

}
