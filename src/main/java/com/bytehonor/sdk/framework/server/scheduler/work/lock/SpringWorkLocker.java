package com.bytehonor.sdk.framework.server.scheduler.work.lock;

public interface SpringWorkLocker {

    public boolean lock(String key, String value, long millis);

    public String which(String key);

    public void expireAt(String key, long timestamp);
}
