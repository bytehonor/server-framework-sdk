package com.bytehonor.sdk.framework.server.scheduler.plan.time;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeGroupPrinter {

    private static final Logger LOG = LoggerFactory.getLogger(TimeGroupPrinter.class);

    public static void print(TimeGroup group) {
        if (group == null) {
            LOG.error("group null");
            return;
        }
        print(group.getCrons());
    }
    
    public static void print(List<TimeCron> crons) {
        if (crons == null) {
            LOG.error("crons null");
            return;
        }
        int size = crons.size();
        LOG.info("crons size:{}", size);
        for (TimeCron cron : crons) {
            LOG.info("cron:{}", cron.toString());
        }
    }
}
