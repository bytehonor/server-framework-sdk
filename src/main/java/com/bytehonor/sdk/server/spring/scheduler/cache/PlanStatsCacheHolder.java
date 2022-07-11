package com.bytehonor.sdk.server.spring.scheduler.cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanStats;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class PlanStatsCacheHolder {

    private static int CAPACITY = 60 * 24 * 512;

    private static Cache<String, TimePlanStats> CACHE = CacheBuilder.newBuilder().initialCapacity(CAPACITY) // 设置初始容量为100
            .maximumSize(128 * CAPACITY) // 设置缓存的最大容量
            .expireAfterWrite(1, TimeUnit.DAYS) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(20) // 设置并发级别为10
            .build(); // .recordStats() // 开启缓存统计

    public static void add(String name) {
        Objects.requireNonNull(name, "name");

        // 一个任务最记录最后时间
        TimePlanStats stats = new TimePlanStats(name);
        CACHE.put(stats.getName(), stats);
    }

    public static TimePlanStats get(String name) {
        Objects.requireNonNull(name, "name");

        return CACHE.getIfPresent(name);
    }
}
