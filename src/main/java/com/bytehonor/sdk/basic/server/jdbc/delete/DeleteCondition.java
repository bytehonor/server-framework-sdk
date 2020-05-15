package com.bytehonor.sdk.basic.server.jdbc.delete;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.server.exception.ServerBasicException;
import com.bytehonor.sdk.basic.server.jdbc.MatchColumnHolder;
import com.bytehonor.sdk.basic.server.query.MatchColumn;

/**
 * 
 * @author lijianqiang
 *
 */
public final class DeleteCondition {

    private Integer limit;

    private final MatchColumnHolder matchHolder;

    private DeleteCondition() {
        this(null);
    }

    private DeleteCondition(Integer limit) {
        this.limit = limit;
        this.matchHolder = MatchColumnHolder.create();
    }

    public static DeleteCondition create() {
        return create(null);
    }

    public static DeleteCondition create(Integer limit) {
        DeleteCondition codition = new DeleteCondition(limit);
        return codition;
    }

    public DeleteCondition and(MatchColumn column) {
        Objects.requireNonNull(column, "column");
        if (StringUtils.isEmpty(column.getKey())) {
            throw new ServerBasicException(44, "column key cann't be empty");
        }
        matchHolder.and(column);
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public MatchColumnHolder getMatchHolder() {
        return matchHolder;
    }

}
