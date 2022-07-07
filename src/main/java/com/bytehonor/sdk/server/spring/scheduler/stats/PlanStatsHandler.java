package com.bytehonor.sdk.server.spring.scheduler.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimePlanExecutor;

public class PlanStatsHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanExecutor.class);

    private static final ConcurrentHashMap<String, PlanStats> MAP = new ConcurrentHashMap<String, PlanStats>();

    public static void put(PlanStats stats) {
        Objects.requireNonNull(stats, "stats");

        MAP.put(stats.getName(), stats);
    }

    public static PlanStats get(String name) {
        Objects.requireNonNull(name, "name");

        return MAP.get(name);
    }

    public static void increase(String name) {
        PlanStats stats = get(name);
        if (stats == null) {
            stats = PlanStats.of(name);
            put(stats);
        }
        stats.setTime(System.currentTimeMillis());
        stats.setTotal(stats.getTotal() + 1);
    }

    public static List<PlanStats> list() {
        int size = size();
        if (size < 1) {
            return new ArrayList<PlanStats>();
        }
        List<PlanStats> list = new ArrayList<PlanStats>(size * 2);
        for (Entry<String, PlanStats> item : MAP.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }

    public static int size() {
        return MAP.size();
    }

    public static void print() {
        for (Entry<String, PlanStats> item : MAP.entrySet()) {
            PlanStats stats = item.getValue();
            LOG.info("name:{}, time:{}, total:{}", stats.getName(), LocalDateTimeUtils.format(stats.getTime()),
                    stats.getTotal());
        }
    }
}
