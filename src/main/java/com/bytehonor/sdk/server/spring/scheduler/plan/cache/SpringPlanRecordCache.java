package com.bytehonor.sdk.server.spring.scheduler.plan.cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class SpringPlanRecordCache {

    private static final int CAPACITY = 4096;

    private Cache<String, Long> cache;

    private SpringPlanRecordCache() {
        cache = CacheBuilder.newBuilder().initialCapacity(CAPACITY) // 设置初始容量为100
                .maximumSize(128 * CAPACITY) // 设置缓存的最大容量
                .expireAfterWrite(2, TimeUnit.DAYS) // 设置缓存在写入一分钟后失效
                .concurrencyLevel(20) // 设置并发级别为10
                .build(); // .recordStats() // 开启缓存统计
    }

    private static class LazyHolder {
        private static SpringPlanRecordCache SINGLE = new SpringPlanRecordCache();
    }

    private static SpringPlanRecordCache self() {
        return LazyHolder.SINGLE;
    }

    public static void add(String name) {
        if (SpringString.isEmpty(name)) {
            return;
        }

        // 一个任务最记录最后时间
        self().cache.put(name, System.currentTimeMillis());
    }

    public static Long get(String name) {
        Objects.requireNonNull(name, "name");

        return self().cache.getIfPresent(name);
    }
}
