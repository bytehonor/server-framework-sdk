package com.bytehonor.sdk.server.spring.scheduler.plan.lock;

import com.bytehonor.sdk.lang.spring.cache.CacheLocker;

/**
 * @author lijianqiang
 *
 */
public class CachePlanLocker extends SpringPlanLocker {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }
}
