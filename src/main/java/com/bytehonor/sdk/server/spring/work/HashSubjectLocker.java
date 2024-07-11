package com.bytehonor.sdk.server.spring.work;

import java.util.HashMap;
import java.util.Map;

public class HashSubjectLocker implements SubjectLocker {

    private final Map<String, String> map;

    public HashSubjectLocker() {
        this.map = new HashMap<String, String>();
    }

    @Override
    public boolean lock(String key, String value, long millis) {
        map.put(key, value);
        return true;
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public void expireAt(String key, long timestamp) {

    }

}
