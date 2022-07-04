package com.bytehonor.sdk.server.spring.scheduler.lock;

import com.bytehonor.sdk.server.spring.cache.CacheLocker;

public class CacheSchedulerLocker extends SchedulerLocker {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }

}
