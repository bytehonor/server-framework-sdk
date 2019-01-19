package com.bytehonor.sdk.server.spring.jdbc.select;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.query.QueryColumn;
import com.bytehonor.sdk.server.spring.query.QueryCondition;

public class SelectPreparedStatementTest {

    private static final Logger LOG = LoggerFactory.getLogger(SelectPreparedStatementTest.class);

    @Test
    public void test() {
        QueryCondition condition = QueryCondition.create(0, 10).and(QueryColumn.eq("name", "john"));
        SelectPreparedStatement statement = SelectPreparedStatement.create("tbl_user", condition);

        LOG.info("sql:{}", statement.toSelectSql("name,age"));
        LOG.info("count:{}", statement.toSelectCountSql("name"));
        List<Object> args = statement.selectArgs();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 1);
    }

}
