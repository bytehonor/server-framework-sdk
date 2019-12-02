package com.bytehonor.sdk.server.spring.jdbc.update;

import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.lang.string.StringCreator;
import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.jdbc.MatchColumnHolder;
import com.bytehonor.sdk.server.spring.query.MatchColumn;

public class UpdatePreparedStatement {

    private final String table;

    private final UpdateColumnHolder updateHolder;

    private final MatchColumnHolder matchHolder;

    private UpdatePreparedStatement(String table) {
        this.table = table;
        this.updateHolder = UpdateColumnHolder.create();
        this.matchHolder = MatchColumnHolder.create();
    }

    public static UpdatePreparedStatement create(String table) {
        Objects.requireNonNull(table, "table");
        return new UpdatePreparedStatement(table);
    }

    public UpdatePreparedStatement set(String key, String value) {
        if (StringUtils.isEmpty(value)) {
            return this;
        }
        this.updateHolder.set(key, value);
        return this;
    }

    public UpdatePreparedStatement set(String key, Long value) {
        this.updateHolder.set(key, value);
        return this;
    }

    public UpdatePreparedStatement set(String key, Integer value) {
        this.updateHolder.set(key, value);
        return this;
    }

    public UpdatePreparedStatement set(String key, Boolean value) {
        this.updateHolder.set(key, value);
        return this;
    }

    public UpdatePreparedStatement set(String key, Double value) {
        this.updateHolder.set(key, value);
        return this;
    }

    public UpdatePreparedStatement match(MatchColumn column) {
        this.matchHolder.and(column);
        return this;
    }

    public String getTable() {
        return table;
    }

    public UpdateColumnHolder getHolder() {
        return updateHolder;
    }

    @Override
    public String toString() {
        return toUpdateSql();
    }

    public String toUpdateSql() {
        return StringCreator.create().append("UPDATE ").append(table).append(updateHolder.toSql()).append(" WHERE 1=1")
                .append(matchHolder.toAndSql()).toString();
    }

    public List<Object> args() {
        if (matchHolder.getArgs().isEmpty()) {
            throw new SpringServerException(44, "update but without any match condition");
        }
        List<Object> args = updateHolder.getArgs();
        args.addAll(matchHolder.getArgs());
        return args;
    }

}
