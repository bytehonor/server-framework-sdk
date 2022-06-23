package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;
import java.util.List;

import com.bytehonor.sdk.server.spring.scheduler.time.TimeCron;

public abstract class TimeCronPlan implements SchedulerPlan {

    public abstract List<TimeCron> crons();

    @Override
    public final boolean accept(LocalDateTime ldt) {
        List<TimeCron> crons = crons();
        // 没有配置的则false
        if (crons == null || crons.isEmpty()) {
            return false;
        }

        for (TimeCron cron : crons) {
            if (cron.match(ldt)) {
                return true;
            }
        }
//        AtomicInteger ai = new AtomicInteger(0);
//        crons.parallelStream().forEach(item -> {
//            if (item.match(ldt)) {
//                ai.incrementAndGet();
//            }
//        });
//        return ai.get() > 0;
        return false;
    }

}
