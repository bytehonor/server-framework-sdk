package com.bytehonor.sdk.server.spring.web.request;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.core.KeyValueMap;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;

public class RequestParserTest {

    @Test
    public void test() {

        KeyValueMap map = new KeyValueMap();
        map.put("create_at", 123L);

        QueryCondition condition = RequestParser.condition(Student.class, QueryLogic.AND, map);
        
        assertTrue("*test*", condition.has(Student::getCreateAt));
    }

}
