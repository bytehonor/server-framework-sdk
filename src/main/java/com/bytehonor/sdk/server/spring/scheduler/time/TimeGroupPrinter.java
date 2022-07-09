package com.bytehonor.sdk.server.spring.scheduler.time;

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
        List<DefineTimeCron> crons = group.getCrons();
        int size = crons.size();
        LOG.info("crons size:{}", size);
        for (DefineTimeCron cron : crons) {
            LOG.info("cron:{}", cron.toString());
        }
    }
}
