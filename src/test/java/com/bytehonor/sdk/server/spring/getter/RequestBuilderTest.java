package com.bytehonor.sdk.server.spring.getter;

import org.junit.Test;

public class RequestBuilderTest {

    @Test
    public void test() {
        RequestBuilder.create(null).eq(Student::getAge, 1).eq(Student::getNickname).build();
    }

}
