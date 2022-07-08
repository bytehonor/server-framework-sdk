package com.bytehonor.sdk.server.spring.scheduler.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanStats;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class PlanStatsCacheHolder {

    private static int CAPACITY = 128;

    private static Cache<String, TimePlanStats> CACHE = CacheBuilder.newBuilder().initialCapacity(CAPACITY) // 设置初始容量为100
            .maximumSize(10 * CAPACITY) // 设置缓存的最大容量
            .expireAfterWrite(2, TimeUnit.DAYS) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(20) // 设置并发级别为10
            .build(); // .recordStats() // 开启缓存统计

    public static void add(String name) {
        Objects.requireNonNull(name, "name");

        // 一个任务最记录最后时间
        TimePlanStats stats = new TimePlanStats(name);
        CACHE.put(stats.getName(), stats);
    }

    public static List<TimePlanStats> list() {
        if (CACHE.size() < 1L) {
            return new ArrayList<TimePlanStats>();
        }

        List<TimePlanStats> list = new ArrayList<TimePlanStats>();
        ConcurrentMap<String, TimePlanStats> map = CACHE.asMap();
        for (Entry<String, TimePlanStats> item : map.entrySet()) {
            list.add(item.getValue());
        }

        return list;
    }
}
