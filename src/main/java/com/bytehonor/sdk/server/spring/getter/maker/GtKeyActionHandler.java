package com.bytehonor.sdk.server.spring.getter.maker;

import com.bytehonor.sdk.lang.spring.constant.JavaValueTypes;
import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.LongGetter;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.server.spring.getter.KeyActionHandler;

public class GtKeyActionHandler implements KeyActionHandler {

    @Override
    public String symbol() {
        return "gt";
    }

    @Override
    public KeyMatcher make(MetaModelField field, String value) {
        if (JavaValueTypes.INTEGER.equals(field.getType())) {
            return KeyMatcher.gt(field.getColumn(), IntegerGetter.optional(value));
        }
        if (JavaValueTypes.LONG.equals(field.getType())) {
            return KeyMatcher.gt(field.getColumn(), LongGetter.optional(value));
        }
        return null;
    }

}
