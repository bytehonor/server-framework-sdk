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
        for (int i = 0; i < 3; i++) {
            statement.rowBegin();
            statement.setColumn("name" + i);
            statement.setColumn("age" + i);
            statement.setColumn("school" + i);
            statement.rowEnd();
        }

        LOG.info("sql:{}", statement.toInsertSql());
        LOG.info("rowsSize:{}", statement.getRowSize());
        assertTrue("test", statement.getRowSize() == 3);
    }

}
