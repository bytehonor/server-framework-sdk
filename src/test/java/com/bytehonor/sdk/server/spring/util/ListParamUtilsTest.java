package com.bytehonor.sdk.server.spring.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class ListParamUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(ListParamUtilsTest.class);

    @Test
    public void testJoinString() {
        List<String> list = Lists.newArrayList("xxx", "yyy");
        LOG.info("testJoinString:{}", ListParamUtils.joinString(list));
        assertTrue("testJoinString", true);
    }

    @Test
    public void testJoinLong() {
        List<Long> list = Lists.newArrayList(1L, 2L);
        LOG.info("testJoinLong:{}", ListParamUtils.joinLong(list));
        assertTrue("testJoinLong", true);
    }

    @Test
    public void testJoinInteger() {
        List<Integer> list = Lists.newArrayList(1, 2);
        LOG.info("testJoinInteger:{}", ListParamUtils.joinInteger(list));
        assertTrue("testJoinInteger", true);
    }

}
