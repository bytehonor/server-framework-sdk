package com.bytehonor.sdk.server.bytehonor.jdbc;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlInjectUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SqlInjectUtilsTest.class);

    @Test
    public void testEscape() {
        String src = "';drop table tbl_other;--";
        String esc = SqlInjectUtils.escape(src);
        LOG.info("escape src:{}, len:{}", src, src.length());
        LOG.info("escape esc:{}, len:{}", esc, esc.length());
        assertTrue(esc.length() == (src.length() + 3), "testEscape");
    }

    @Test
    public void testColumn() {
        String src = "';drop table tbl_other;--";
        String esc = SqlInjectUtils.column(src);
        LOG.info("column esc:{}", esc);
        assertTrue(esc.length() == (src.length() - 2), "testColumn");
    }
}
