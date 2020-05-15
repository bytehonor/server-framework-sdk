package com.bytehonor.sdk.basic.server.query;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.define.constant.HttpConstants;
import com.bytehonor.sdk.basic.server.exception.ServerBasicException;
import com.bytehonor.sdk.basic.server.getter.RequestGetter;
import com.bytehonor.sdk.basic.server.jdbc.MatchColumnHolder;

/**
 * 
 * @author lijianqiang
 *
 */
public final class QueryCondition {

    private static final Logger LOG = LoggerFactory.getLogger(QueryCondition.class);

    private int offset;

    private int limit;

    private QueryOrder order;

    private final MatchColumnHolder matchHolder;

    private QueryCondition() {
        this(0, HttpConstants.LIMIT_MAX, null);
    }

    private QueryCondition(int offset, int limit, QueryOrder order) {
        this.offset = offset;
        this.limit = limit;
        this.order = order;
        this.matchHolder = MatchColumnHolder.create();
    }

    public static QueryCondition create() {
        return create(0, HttpConstants.LIMIT_MAX, null);
    }

    public static QueryCondition create(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");
        int offset = RequestGetter.getOffset(request);
        int limit = RequestGetter.getLimit(request);
        return create(offset, limit);
    }

    public static QueryCondition create(int offset, int limit) {
        return create(offset, limit, null);
    }

    public static QueryCondition create(int offset, int limit, QueryOrder order) {
        QueryCondition codition = new QueryCondition(limit, offset, order);
        return codition;
    }

    public QueryCondition and(MatchColumn column) {
        Objects.requireNonNull(column, "column");
        if (StringUtils.isEmpty(column.getKey())) {
            throw new ServerBasicException(44, "column key cann't be empty");
        }
        matchHolder.and(column);
        return this;
    }

    public QueryCondition orderBy(QueryOrder order) {
        this.order = order;
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

    public MatchColumnHolder getMatchHolder() {
        return matchHolder;
    }

    public String offsetLimitSql() {
        if (limit > HttpConstants.LIMIT_MAX_TOP) {
            LOG.warn("[WARN] limit:{} cann't exceed {}", limit, HttpConstants.LIMIT_MAX_TOP);
            limit = HttpConstants.LIMIT_MAX_TOP;
        }
        StringBuilder sb = new StringBuilder(" LIMIT ").append(offset).append(",").append(limit);
        return sb.toString();
    }

}
