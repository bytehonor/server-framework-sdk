package com.bytehonor.sdk.server.spring.scheduler.handler;

import com.bytehonor.sdk.lang.spring.cache.CacheLocker;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;

public class LocalTaskHandler extends TaskHandler {

    @Override
    public boolean lock(String key) {
        return CacheLocker.lock(key);
    }

    @Override
    public boolean isPause(String name) {
        return PlanPauseCacheHolder.isPause(name);
    }

    @Override
    public void pause(String name) {
        PlanPauseCacheHolder.pause(name);
    }

    @Override
    public void play(String name) {
        PlanPauseCacheHolder.play(name);
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
