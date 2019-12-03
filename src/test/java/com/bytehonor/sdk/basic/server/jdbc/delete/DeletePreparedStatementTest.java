package com.bytehonor.sdk.basic.server.jdbc.delete;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.basic.server.query.MatchColumn;

public class DeletePreparedStatementTest {
    private static final Logger LOG = LoggerFactory.getLogger(DeletePreparedStatementTest.class);

    @Test
    public void test() {
        DeletePreparedStatement statement = DeletePreparedStatement.create("tbl_user");
        statement.match(MatchColumn.eq("name", "john"));
        statement.match(MatchColumn.egt("create_at", System.currentTimeMillis()));

        LOG.info("sql:{}", statement.toDeleteRealSql());
        LOG.info("sql:{}", statement.toDeleteLogicSql());
        List<Object> args = statement.args();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 2);
    }

}
