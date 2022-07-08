package com.bytehonor.sdk.server.spring.scheduler.handler;

public interface TaskLock {

    public boolean lock(String key);
}
