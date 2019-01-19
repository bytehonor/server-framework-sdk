package com.bytehonor.sdk.server.spring.jdbc.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.server.spring.jdbc.SqlConstants;

public class UpdateColumnHolder {

    private StringBuilder sb;

    private List<Object> args;

    private UpdateColumnHolder() {
        sb = new StringBuilder();
        args = new ArrayList<Object>();
    }

    public static UpdateColumnHolder create() {
        return new UpdateColumnHolder();
    }

    public UpdateColumnHolder append(String key, Object value) {
        this.append(new UpdateColumn(key, value));
        return this;
    }

    /**
     * uncheck inject
     * 
     * @param column
     * @return
     */
    public UpdateColumnHolder append(UpdateColumn column) {
        Objects.requireNonNull(column, "column");
        if (StringUtils.isEmpty(column.getKey()) || column.getValue() == null) {
            return this;
        }
        // TODO value inject filter
        if (this.args.isEmpty()) {
            this.sb.append(SqlConstants.SET);
        } else {
            this.sb.append(SqlConstants.CON);
        }
        this.sb.append(column.getKey()).append(SqlConstants.PARA_HOLD);
        this.args.add(column.getValue());
        return this;
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        return sb.toString();
    }

    public List<Object> getArgs() {
        return args;
    }
}
