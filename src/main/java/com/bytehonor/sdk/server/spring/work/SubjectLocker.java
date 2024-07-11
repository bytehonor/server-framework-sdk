package com.bytehonor.sdk.server.spring.work;

public interface SubjectLocker {

    public boolean lock(String key, String value, long millis);

    public String get(String key);

    public void expireAt(String key, long timestamp);
}
