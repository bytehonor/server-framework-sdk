package com.bytehonor.sdk.framework.server.scheduler.plan.lock;

import com.bytehonor.sdk.framework.lang.cache.CacheLocker;

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
