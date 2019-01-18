package com.bytehonor.sdk.server.spring.query;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnAndHolderTest {

    private static final Logger LOG = LoggerFactory.getLogger(ColumnAndHolderTest.class);

    @Test
    public void test() {
        ColumnAndHolder sac = ColumnAndHolder.create();
        sac.eq("name", "john").neq("neq", 1).egt("egt", 2).elt("elt", 3).lt("lt", 4).between("age", 5, 6);

        LOG.info("sql:{}", sac.toAndSql());

        List<Object> args = sac.getArgs();
        LOG.info("args:{}", args);
        assertTrue("test", args.size() == 7);
    }

}
