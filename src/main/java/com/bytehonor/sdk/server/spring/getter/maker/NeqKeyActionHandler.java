package com.bytehonor.sdk.server.spring.getter.maker;

import com.bytehonor.sdk.lang.spring.constant.JavaValueTypes;
import com.bytehonor.sdk.lang.spring.getter.BooleanGetter;
import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.LongGetter;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.server.spring.getter.KeyActionHandler;

public class NeqKeyActionHandler implements KeyActionHandler {

    @Override
    public String symbol() {
        return "neq";
    }

    @Override
    public KeyMatcher make(MetaModelField field, String value) {
        if (JavaValueTypes.BOOLEAN.equals(field.getType())) {
            return KeyMatcher.neq(field.getColumn(), BooleanGetter.optional(value));
        }
        if (JavaValueTypes.INTEGER.equals(field.getType())) {
            return KeyMatcher.neq(field.getColumn(), IntegerGetter.optional(value));
        }
        if (JavaValueTypes.LONG.equals(field.getType())) {
            return KeyMatcher.neq(field.getColumn(), LongGetter.optional(value));
        }
        return KeyMatcher.neq(field.getColumn(), value);
    }

}
