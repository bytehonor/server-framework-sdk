package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.scheduler.handler.TaskManage;

/**
 * @author lijianqiang
 *
 */
public class TimePlanFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TimePlanFactory.class);

    private static final ConcurrentHashMap<String, TimePlanHolder> MAP = new ConcurrentHashMap<String, TimePlanHolder>();

    public static void add(TimePlan plan) {
        Objects.requireNonNull(plan, "plan");

        TimePlanHolder holder = new TimePlanHolder(plan);
        MAP.put(holder.getName(), holder);
    }

    public static List<TimePlan> listPlan(TaskManage manage) {
        List<TimePlan> list = new ArrayList<TimePlan>();
        for (Entry<String, TimePlanHolder> item : MAP.entrySet()) {
            if (isPause(manage, item.getKey())) {
                LOG.warn("name:{} isPause", item.getKey());
                continue;
            }
            LOG.info("name:{} prepare", item.getKey());
            list.add(item.getValue().getPlan());
        }
        return list;
    }

    public static List<TimePlanStatus> listStatus(TaskManage manage) {
        List<TimePlanStatus> list = new ArrayList<TimePlanStatus>();
        for (Entry<String, TimePlanHolder> item : MAP.entrySet()) {
            list.add(new TimePlanStatus(item.getKey(), isPause(manage, item.getKey())));
        }
        return list;
    }

    private static boolean isPause(TaskManage manage, String name) {
        return manage != null && manage.isPause(name);
    }

    public static TimePlan get(String name) {
        Objects.requireNonNull(name, "name");
        TimePlanHolder holder = MAP.get(name);
        if (holder == null) {
            return null;
        }
        return holder.getPlan();
    }
}
