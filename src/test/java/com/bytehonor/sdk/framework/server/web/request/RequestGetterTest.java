package com.bytehonor.sdk.framework.server.web.request;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class RequestGetterTest {

    @Test
    public void testLongs() {
        List<Long> list = RequestGetter.longs("1, 2, ,3,");
        assertEquals(3, list.size());
        assertEquals(Long.valueOf(1L), list.get(0));
        assertEquals(Long.valueOf(2L), list.get(1));
        assertEquals(Long.valueOf(3L), list.get(2));
    }

    @Test
    public void testIntegers() {
        List<Integer> list = RequestGetter.integers("10, 20, ,30,");
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
        assertEquals(Integer.valueOf(30), list.get(2));
    }

    @Test
    public void testStrings() {
        List<String> list = RequestGetter.strings("a, b,  ,c,");
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

}
