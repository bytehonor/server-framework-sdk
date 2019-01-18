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
        condition.appendColumn(TableColumn.eq("eq", "111")).appendColumn(TableColumn.neq("neq", 222))
                .appendColumn(TableColumn.lt("lt", "333")).appendColumn(TableColumn.elt("elt", 4444))
                .appendColumn(TableColumn.gt("gt", "5")).appendColumn(TableColumn.eq("name", "john"));

        condition.setOffset(10);
        condition.setOrder(QueryOrder.descOf("id"));

        LOG.info("list:{}", condition.conditionListSql());
        LOG.info("count:{}", condition.conditionCountSql());

        List<Object> args = condition.conditionArgs();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 6);
    }

}
