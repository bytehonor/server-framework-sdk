package com.bytehonor.sdk.server.spring.getter;

import com.bytehonor.sdk.lang.spring.match.KeyMatcher;

public interface KeyMaker {

    public String symbol();

    public KeyMatcher make(KeyValue kv);
}
