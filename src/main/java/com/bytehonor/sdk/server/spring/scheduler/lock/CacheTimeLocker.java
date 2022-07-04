package com.bytehonor.sdk.server.spring.scheduler.lock;

import com.bytehonor.sdk.server.spring.cache.CacheLocker;

public class CacheTimeLocker extends TimeLocker {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }

}
