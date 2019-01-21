package com.bytehonor.sdk.server.spring.jdbc.insert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertPreparedStatementTest {

    private static final Logger LOG = LoggerFactory.getLogger(InsertPreparedStatementTest.class);

    @Test
    public void test() {
        InsertPreparedStatement statement = InsertPreparedStatement.create("tbl_user", "name,age,school");
        for (int i = 0; i < 1; i++) {
            statement.rowBegin();
            statement.setColumn("name" + i);
            statement.setColumn(i);
            statement.setColumn("school';drop table tbl_other;--");
            statement.rowEnd();
        }

        LOG.info("sql:{}", statement.toInsertSql());
        LOG.info("rowsSize:{}", statement.getRowSize());
        assertTrue("test", statement.getRowSize() == 1);
    }

}
