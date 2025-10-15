package com.bytehonor.sdk.framework.server.scheduler.plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.util.TimeFormatUtils;
import com.bytehonor.sdk.framework.server.scheduler.plan.cache.SpringPlanPauseCache;
import com.bytehonor.sdk.framework.server.scheduler.plan.cache.SpringPlanRecordCache;

/**
 * @author lijianqiang
 *
 */
public final class SpringPlanFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanFactory.class);

    private static final ConcurrentHashMap<String, SpringPlan> MAP = new ConcurrentHashMap<String, SpringPlan>();

    public static void add(SpringPlan plan) {
        Java.requireNonNull(plan, "plan");

        String name = plan.getClass().getSimpleName();
        LOG.info("name:{}", name);
        MAP.put(name, plan);
    }

    public static List<SpringPlan> listPlanPlay() {
        List<SpringPlan> list = new ArrayList<SpringPlan>();
        for (Entry<String, SpringPlan> item : MAP.entrySet()) {
            if (isPaused(item.getKey())) {
                LOG.warn("name:{} paused", item.getKey());
                continue;
            }
            LOG.debug("name:{} prepare", item.getKey());
            list.add(item.getValue());
        }
        return list;
    }

    public static List<SpringPlanStatus> listPlanStatus() {
        List<SpringPlanStatus> list = new ArrayList<SpringPlanStatus>();
        for (Entry<String, SpringPlan> item : MAP.entrySet()) {
            list.add(toStatus(item.getKey()));
        }
        list.sort(new Comparator<SpringPlanStatus>() {

            @Override
            public int compare(SpringPlanStatus o1, SpringPlanStatus o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }

    public static SpringPlanStatus getPlanStatus(String name) {
        Java.requireNonNull(name, "name");
        required(name);

        return toStatus(name);
    }

    private static SpringPlanStatus toStatus(String name) {
        SpringPlanStatus model = new SpringPlanStatus(name);
        model.setPaused(isPaused(name));
        Long time = SpringPlanRecordCache.get(name);
        if (time != null) {
            model.setTime(time);
            model.setDate(TimeFormatUtils.format(time));
        }
        return model;
    }

    private static boolean isPaused(String name) {
        return SpringPlanPauseCache.isPaused(name);
    }

    public static SpringPlan required(String name) {
        Java.requireNonNull(name, "name");

        SpringPlan plan = optional(name);
        Java.requireNonNull(plan, name);

        return plan;
    }

    public static SpringPlan optional(String name) {
        Java.requireNonNull(name, "name");

        return MAP.get(name);
    }
}
