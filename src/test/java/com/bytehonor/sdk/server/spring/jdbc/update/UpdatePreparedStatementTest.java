package com.bytehonor.sdk.server.spring.jdbc.update;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePreparedStatementTest {

    private static final Logger LOG = LoggerFactory.getLogger(UpdatePreparedStatementTest.class);

    @Test
    public void test() {
        UpdatePreparedStatement statement = UpdatePreparedStatement.create("tbl_user");
        statement.set("name", "john");
        statement.set("age", 13);
        statement.set("school", null);
        statement.set("create_at", System.currentTimeMillis());

        LOG.info("sql:{}", statement.toUpdateSql());
        List<Object> args = statement.updateArgs();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 3);
    }

}
