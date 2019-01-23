package com.bytehonor.sdk.server.spring.jdbc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlInjectUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SqlInjectUtilsTest.class);

    @Test
    public void test() {
        String src = "';drop table tbl_other;--";
        String esc = SqlInjectUtils.escape(src);
        LOG.info("esc:{}", esc);
        assertTrue("test", esc.length() == (src.length() + 1));
    }

}
