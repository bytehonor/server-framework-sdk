package com.bytehonor.sdk.server.spring.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.protocol.core.server.constant.HttpConstants;
import com.bytehonor.sdk.server.spring.getter.RequestGetter;
import com.bytehonor.sdk.server.spring.string.StringCreator;

/**
 * 
 * @author lijianqiang
 *
 */
public final class QueryCondition {

    private static final Logger LOG = LoggerFactory.getLogger(QueryCondition.class);

    private int offset;

    private int limit;

    private ColumnAndHolder columnAndHolder;

    private QueryOrder order;

    private QueryCondition() {
        this.offset = 0;
        this.limit = HttpConstants.LIMIT_MAX;
        this.columnAndHolder = ColumnAndHolder.create();
    }

    public static QueryCondition create() {
        return create(ColumnAndHolder.create(), 0, HttpConstants.LIMIT_MAX, null);
    }

    public static QueryCondition create(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");
        int offset = RequestGetter.getOffset(request);
        int limit = RequestGetter.getLimit(request);
        return create(offset, limit);
    }

    public static QueryCondition create(int offset, int limit) {
        return create(ColumnAndHolder.create(), offset, limit, null);
    }

    public static QueryCondition create(ColumnAndHolder columnAndHolder, int offset, int limit, QueryOrder order) {
        Objects.requireNonNull(columnAndHolder, "columnAndHolder");
        QueryCondition codition = new QueryCondition();
        codition.setColumnAndHolder(columnAndHolder);
        codition.setLimit(limit);
        codition.setOffset(offset);
        codition.setOrder(order);
        return codition;
    }

    public QueryCondition appendColumn(TableColumn column) {
        columnAndHolder.append(column);
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public QueryOrder getOrder() {
        return order;
    }

    public void setOrder(QueryOrder order) {
        this.order = order;
    }

    public ColumnAndHolder getColumnAndHolder() {
        return columnAndHolder;
    }

    public void setColumnAndHolder(ColumnAndHolder columnAndHolder) {
        this.columnAndHolder = columnAndHolder;
    }

    private String orderBySql() {
        if (order == null) {
            return null;
        }
        return order.toSql();
    }

    private String offsetLimitSql() {
        if (limit > HttpConstants.LIMIT_MAX_TOP) {
            LOG.warn("limit:{} > {}, has reset", limit, HttpConstants.LIMIT_MAX_TOP);
            limit = HttpConstants.LIMIT_MAX_TOP;
        }
        StringBuilder sb = new StringBuilder(" LIMIT ").append(offset).append(",").append(limit);
        return sb.toString();
    }

    private String columnAndSql() {
        if (columnAndHolder == null) {
            return null;
        }
        return columnAndHolder.toAndSql();
    }

    public String conditionSql() {
        return StringCreator.create().append(columnAndSql()).append(orderBySql()).append(offsetLimitSql()).toString();
    }

    public List<Object> conditionArgs() {
        if (columnAndHolder == null) {
            return new ArrayList<Object>();
        }
        return columnAndHolder.getArgs();
    }

    @Override
    public String toString() {
        return conditionSql();
    }
}
