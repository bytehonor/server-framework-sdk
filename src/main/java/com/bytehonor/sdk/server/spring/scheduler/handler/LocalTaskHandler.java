package com.bytehonor.sdk.server.spring.scheduler.handler;

import com.bytehonor.sdk.lang.spring.cache.CacheLocker;

public class LocalTaskHandler extends TaskHandler {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }

//    @Override
//    public void save(PlanStats stats) {
//        PlanStatsCacheHolder.put(stats);
//    }
//
//    @Override
//    public List<PlanStats> list(int size) {
//        List<PlanStats> all = PlanStatsCacheHolder.list();
//        if (CollectionUtils.isEmpty(all)) {
//            return all;
//        }
//        return null;
//    }

}
