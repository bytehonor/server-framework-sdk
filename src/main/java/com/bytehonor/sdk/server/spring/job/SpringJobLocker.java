package com.bytehonor.sdk.server.spring.job;

public interface SpringJobLocker {

    public boolean lock(String key, String value, long millis);

    public String get(String key);

    public void expireAt(String key, long timestamp);
}
