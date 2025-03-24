package com.bytehonor.sdk.server.spring.scheduler.plan;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.scheduler.plan.time.TimeCron;
import com.bytehonor.sdk.server.spring.scheduler.plan.time.TimeGroup;
import com.bytehonor.sdk.server.spring.scheduler.plan.time.TimeGroup.TimeGroupBuilder;
import com.bytehonor.sdk.server.spring.scheduler.plan.time.TimeGroupPrinter;

public class TimeGroupTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeGroupTest.class);

    @Test
    public void test() {
        TimeGroupBuilder builder = TimeGroup.builder();
        builder.mintues(1, 2, 3, 4, 5).hours(1, 2, 3, 4, 5).done();
        builder.mintues(6, 7, 8, 9, 10).hours(6, 7, 8, 9, 10).done();
        builder.every(0, 2).mintues(11, 12, 13, 14, 15).hours(11, 12, 13, 14, 15).done(); // every(0, 2) 不生效
        TimeGroup group = builder.build();
        List<TimeCron> crons = group.getCrons();
        LOG.info("test size:{}", crons.size());
        for (TimeCron cron : crons) {
            LOG.info("test {}", cron.toString());
        }
        boolean isOk1 = group.match(LocalDateTime.of(2022, 7, 5, 5, 5));
        boolean isOk2 = group.match(LocalDateTime.of(2022, 7, 5, 9, 9));
        boolean isOk3 = group.match(LocalDateTime.of(2022, 7, 5, 11, 15));
        boolean isOk4 = group.match(LocalDateTime.of(2022, 7, 5, 5, 15)) == false;
        boolean isOk5 = group.match(LocalDateTime.of(2022, 7, 5, 16, 1)) == false;

        assertTrue("*test2*", isOk1 && isOk2 && isOk3 && isOk4 && isOk5);
    }

    @Test
    public void test2() {
        TimeGroupBuilder builder = TimeGroup.builder();
        TimeGroup group = builder.every().build();
        List<TimeCron> crons = group.getCrons();
        int size = crons.size();
        LOG.info("test2 size:{}", size);
        for (TimeCron cron : crons) {
            LOG.info("test2 {}", cron.toString());
        }

        boolean isOk1 = group.match(LocalDateTime.of(2022, 7, 5, 0, 1));
        assertTrue("*test2*", size == 1 && isOk1);
    }

    @Test
    public void test3() {
        TimeGroupBuilder builder = TimeGroup.builder();
        TimeGroup group = builder.every(0, 2).every(1, 3).build(); // every(0, 2) 不生效

        TimeGroupPrinter.print(group);

        List<TimeCron> crons = group.getCrons();
        int size = crons.size();
        LOG.info("test3 size:{}", size);
        for (TimeCron cron : crons) {
            LOG.info("test3 {}", cron.toString());
        }
        boolean isOk1 = group.match(LocalDateTime.of(2022, 7, 5, 0, 1));
        boolean isOk2 = group.match(LocalDateTime.of(2022, 7, 5, 0, 3)) == false;
        boolean isOk3 = group.match(LocalDateTime.of(2022, 7, 5, 0, 4));
        boolean isOk = isOk1 && isOk2 && isOk3;

        assertTrue("*test3*", size == 20 && isOk);
    }

}
