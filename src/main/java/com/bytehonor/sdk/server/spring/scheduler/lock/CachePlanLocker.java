package com.bytehonor.sdk.server.spring.scheduler.lock;

import com.bytehonor.sdk.lang.spring.cache.CacheLocker;

/**
 * @author lijianqiang
 *
 */
public class CachePlanLocker extends PlanLocker {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }
}
