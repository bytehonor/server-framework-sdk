package com.bytehonor.sdk.server.spring.jdbc.insert;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.server.spring.exception.SpringServerException;
import com.bytehonor.sdk.server.spring.jdbc.SqlInjectUtils;
import com.bytehonor.sdk.server.spring.string.StringCreator;

public class InsertPreparedStatement {

    private final String table;

    private final String columns;

    private final StringBuilder values;

    private final int columnSize;

    private int rowSize;

    private int columnIndex;

    private boolean rowEnd;

    private InsertPreparedStatement(String table, String columns) {
        this.table = table;
        this.columns = columns;
        this.values = new StringBuilder();
        this.rowSize = 0;
        this.rowEnd = true;
        this.columnSize = countCountSize(columns);
    }

    public static InsertPreparedStatement create(String table, String columns) {
        Objects.requireNonNull(table, "table");
        Objects.requireNonNull(columns, "columns");
        return new InsertPreparedStatement(table, columns);
    }

    public String getTable() {
        return table;
    }

    public String getColumns() {
        return columns;
    }

    public int getRowSize() {
        return rowSize;
    }

    private int countCountSize(String columns) {
        int size = 0;
        int len = columns.length();
        for (int i = 0; i < len; i++) {
            if (',' == columns.charAt(i)) {
                size++;
            }
        }
        size++;
        return size;
    }

    public InsertPreparedStatement rowBegin() {
        if (!rowEnd) {
            throw new SpringServerException(44, "row does not end");
        }
        rowEnd = false;
        columnIndex = 0;
        rowSize++;
        if (rowSize > 1) {
            values.append(",(");
        } else {
            values.append("(");
        }
        return this;
    }

    public InsertPreparedStatement rowEnd() {
        if (columnIndex != columnSize) {
            throw new SpringServerException(44, "columnSize not fit");
        }
        rowEnd = true;
        values.append(")");
        return this;
    }

    public InsertPreparedStatement setColumn(String value) {
        Objects.requireNonNull(value, "value");
        if (StringUtils.isEmpty(value) == false) {
            value = SqlInjectUtils.escape(value);
        }
        return setColumnValue(value);
    }

    public InsertPreparedStatement setColumn(Long value) {
        Objects.requireNonNull(value, "value");
        return setColumnValue(value);
    }

    public InsertPreparedStatement setColumn(Integer value) {
        Objects.requireNonNull(value, "value");
        return setColumnValue(value);
    }

    public InsertPreparedStatement setColumn(Boolean value) {
        Objects.requireNonNull(value, "value");
        return setColumnValue(value);
    }

    public InsertPreparedStatement setColumn(Double value) {
        Objects.requireNonNull(value, "value");
        return setColumnValue(value);
    }

    private InsertPreparedStatement setColumnValue(Object value) {
        Objects.requireNonNull(value, "value");
        columnIndex++;
        if (columnIndex > 1) {
            values.append(",");
        }
        values.append(value);
        return this;
    }

    public String toInsertSql() {
        if (rowSize < 1) {
            throw new SpringServerException(44, "rows empty");
        }
        return StringCreator.create().append("INSERT INTO ").append(table).append(" (").append(columns)
                .append(") VALUES ").append(values.toString()).toString();
    }

}
