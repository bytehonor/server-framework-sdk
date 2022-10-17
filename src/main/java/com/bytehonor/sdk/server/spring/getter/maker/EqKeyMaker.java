package com.bytehonor.sdk.server.spring.getter.maker;

import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.server.spring.getter.KeyMaker;
import com.bytehonor.sdk.server.spring.getter.KeyValue;

public class EqKeyMaker implements KeyMaker {

    @Override
    public String symbol() {
        return "eq";
    }

    @Override
    public KeyMatcher make(KeyValue kv) {
        
        return null;
    }

}
