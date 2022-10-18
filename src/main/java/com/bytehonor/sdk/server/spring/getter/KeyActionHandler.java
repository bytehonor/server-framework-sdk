package com.bytehonor.sdk.server.spring.getter;

import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;

public interface KeyActionHandler {

    public String symbol();

    public KeyMatcher make(MetaModelField field, String value);
}
