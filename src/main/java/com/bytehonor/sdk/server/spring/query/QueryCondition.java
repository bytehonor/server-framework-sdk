package com.bytehonor.sdk.server.spring.query;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.bytehonor.protocol.core.server.constant.HttpConstants;
import com.bytehonor.sdk.server.spring.getter.RequestGetter;
import com.bytehonor.sdk.server.spring.jdbc.MatchColumnHolder;

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

    private QueryCondition(MatchColumnHolder matchHolder) {
        this.offset = 0;
        this.limit = HttpConstants.LIMIT_MAX;
        this.matchHolder = matchHolder;
    }

    public static QueryCondition create() {
        return create(MatchColumnHolder.create(), 0, HttpConstants.LIMIT_MAX, null);
    }

    public static QueryCondition create(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");
        int offset = RequestGetter.getOffset(request);
        int limit = RequestGetter.getLimit(request);
        return create(offset, limit);
    }

    public static QueryCondition create(int offset, int limit) {
        return create(MatchColumnHolder.create(), offset, limit, null);
    }

    public static QueryCondition create(MatchColumnHolder matchHolder, int offset, int limit, QueryOrder order) {
        Objects.requireNonNull(matchHolder, "matchHolder");
        QueryCondition codition = new QueryCondition(matchHolder);
        codition.setLimit(limit);
        codition.setOffset(offset);
        codition.setOrder(order);
        return codition;
    }

    public QueryCondition and(MatchColumn column) {
        Objects.requireNonNull(column, "column");
        if (StringUtils.isEmpty(column.getKey()) == false) {
            matchHolder.and(column);
        }
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
