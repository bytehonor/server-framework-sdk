package com.bytehonor.sdk.server.spring.cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.lang.spring.util.StringObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheLocker {

    private static int CAPACITY = 4096;

    private static Cache<String, Boolean> CACHE = CacheBuilder.newBuilder().initialCapacity(CAPACITY) // 设置初始容量为100
            .maximumSize(1024 * CAPACITY) // 设置缓存的最大容量
            .expireAfterWrite(4, TimeUnit.HOURS) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(20) // 设置并发级别为10
            .build(); // .recordStats() // 开启缓存统计

    public static boolean lock(String key) {
        if (StringObject.isEmpty(key)) {
            return false;
        }
        if (CACHE.getIfPresent(key) != null) {
            return false;
        }
        CACHE.put(key, true);
        return true;
    }

    public static void invalidate(String key) {
        Objects.requireNonNull(key, "key");
        CACHE.invalidate(key);
    }
}
