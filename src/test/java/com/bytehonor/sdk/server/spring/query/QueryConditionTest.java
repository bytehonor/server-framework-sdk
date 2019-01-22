package com.bytehonor.sdk.server.spring.query;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryConditionTest {

    private static final Logger LOG = LoggerFactory.getLogger(QueryConditionTest.class);

    @Test
    public void test() {
        QueryCondition condition = QueryCondition.create();
        condition.and(MatchColumn.eq("eq", "111")).and(MatchColumn.neq("neq", 222)).and(MatchColumn.lt("lt", 333))
                .and(MatchColumn.elt("elt", 4444)).and(MatchColumn.gt("gt", 5)).and(MatchColumn.eq("name", "john"));

        condition.setOffset(10);
        condition.setOrder(QueryOrder.descOf("id"));

        LOG.info("offset:{}", condition.offsetLimitSql());
        LOG.info("order:{}", condition.getOrder().toSql());
        LOG.info("conditon:{}", condition.getMatchHolder().toAndSql());
        List<Object> args = condition.getMatchHolder().getArgs();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 6);
    }

}
