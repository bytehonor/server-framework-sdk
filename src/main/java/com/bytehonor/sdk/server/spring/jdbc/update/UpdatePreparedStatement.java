package com.bytehonor.sdk.server.spring.jdbc.update;

import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.server.spring.jdbc.SqlConstants;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class UpdatePreparedStatement {

    private final String table;

    private final UpdateColumnHolder holder;

    private UpdatePreparedStatement(String table) {
        this.table = table;
        this.holder = UpdateColumnHolder.create();
    }

    public static UpdatePreparedStatement create(String table) {
        Objects.requireNonNull(table, "table");
        return new UpdatePreparedStatement(table);
    }

    public UpdatePreparedStatement set(String key, Object value) {
        this.holder.append(key, value);
        return this;
    }

    public String getTable() {
        return table;
    }

    public UpdateColumnHolder getHolder() {
        return holder;
    }

    @Override
    public String toString() {
        return toUpdateSql();
    }

    public String toUpdateSql() {
        return StringCreator.create().append(SqlConstants.UPDATE).append(table).append(holder.toSql()).toString();
    }

    public List<Object> updateArgs() {
        return holder.getArgs();
    }

}
