package com.bytehonor.sdk.server.spring.scheduler.time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanPauseCacheHolder;
import com.bytehonor.sdk.server.spring.scheduler.cache.PlanRecordCacheHolder;

/**
 * @author lijianqiang
 *
 */
public class TimePlanFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanFactory.class);

    private static final ConcurrentHashMap<String, TimePlan> MAP = new ConcurrentHashMap<String, TimePlan>();

    public static void add(TimePlan plan) {
        Objects.requireNonNull(plan, "plan");

        String name = plan.getClass().getSimpleName();
        LOG.info("name:{}", name);
        MAP.put(name, plan);
    }

    public static List<TimePlan> listPlanPlay() {
        List<TimePlan> list = new ArrayList<TimePlan>();
        for (Entry<String, TimePlan> item : MAP.entrySet()) {
            if (isPaused(item.getKey())) {
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
            list.add(toStatus(item.getKey()));
        }
        list.sort(new Comparator<TimePlanStatus>() {

            @Override
            public int compare(TimePlanStatus o1, TimePlanStatus o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }

    public static TimePlanStatus getPlanStatus(String name) {
        Objects.requireNonNull(name, "name");
        required(name);

        return toStatus(name);
    }

    private static TimePlanStatus toStatus(String name) {
        TimePlanStatus model = new TimePlanStatus(name);
        model.setPaused(isPaused(name));
        Long time = PlanRecordCacheHolder.get(name);
        if (time != null) {
            model.setTime(time);
            model.setDate(TimeFormatUtils.format(time));
        }
        return model;
    }

    private static boolean isPaused(String name) {
        return PlanPauseCacheHolder.isPaused(name);
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
