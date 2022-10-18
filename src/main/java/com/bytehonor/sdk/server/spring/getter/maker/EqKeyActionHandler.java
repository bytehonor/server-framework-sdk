package com.bytehonor.sdk.server.spring.getter.maker;

import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.server.spring.getter.KeyActionHandler;

public class EqKeyActionHandler implements KeyActionHandler {

    @Override
    public String symbol() {
        return "eq";
    }

    @Override
    public KeyMatcher make(MetaModelField field, String value) {
        return KeyMatcher.of(field.getKey(), value, field.getType(), SqlOperator.EQ);
    }

}
