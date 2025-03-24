package com.bytehonor.sdk.server.spring.scheduler.plan;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.scheduler.plan.time.DefineTimeCron;
import com.bytehonor.sdk.server.spring.scheduler.plan.time.TimeCronBuilder;

public class TimeCronBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeCronBuilderTest.class);

    @Test
    public void test() {
        List<DefineTimeCron> list = TimeCronBuilder.make().mintueAt(2, 11, 22, 31, 43, 52)
                .hourAt(10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21).build();
        boolean isOk = list != null && list.size() == (6 * 11);

        for (DefineTimeCron cron : list) {
            LOG.info("m:{}, h:{}, d:{}", cron.getMinute(), cron.getHour(), cron.getDay());
        }

        assertTrue("*testGet*", isOk);
    }

}
