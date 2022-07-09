package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;

/**
 * @author lijianqiang
 *
 */
public class TimePlanFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanFactory.class);

    private static final ConcurrentHashMap<String, TimePlan> MAP = new ConcurrentHashMap<String, TimePlan>();

    public static void add(TimePlan plan) {
        Objects.requireNonNull(plan, "plan");

        MAP.put(plan.getClass().getSimpleName(), plan);
    }

    public static List<TimePlan> listPlanPlay() {
        List<TimePlan> list = new ArrayList<TimePlan>();
        for (Entry<String, TimePlan> item : MAP.entrySet()) {
            if (isPause(item.getKey())) {
                LOG.warn("name:{} paused", item.getKey());
                continue;
            }
            LOG.debug("name:{} prepare", item.getKey());
            list.add(item.getValue());
        }
        return list;
    }

    public static List<TimePlanStatus> listPlanStatus() {
        List<TimePlanStatus> list = new ArrayList<TimePlanStatus>();
        for (Entry<String, TimePlan> item : MAP.entrySet()) {
            list.add(new TimePlanStatus(item.getKey(), isPause(item.getKey())));
        }
        return list;
    }

    private static boolean isPause(String name) {
        return PlanPauseCacheHolder.isPause(name);
    }

    public static TimePlan required(String name) {
        Objects.requireNonNull(name, "name");

        TimePlan plan = optional(name);
        Objects.requireNonNull(plan, name);

        return plan;
    }

    public static TimePlan optional(String name) {
        Objects.requireNonNull(name, "name");

        return MAP.get(name);
    }
}
