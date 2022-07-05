package com.bytehonor.sdk.server.spring.scheduler.time;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup.TimeGroupBuilder;

public class TimeGroupTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeGroupTest.class);

    @Test
    public void test() {
        TimeGroupBuilder builder = TimeGroup.builder();
        builder.mintues(1, 2, 3, 4, 5).hours(1, 2, 3, 4, 5).done();
        builder.mintues(6, 7, 8, 9, 10).hours(6, 7, 8, 9, 10).done();
        builder.mintues(11, 12, 13, 14, 15).hours(11, 12, 13, 14, 15).done();
        TimeGroup group = builder.build();
        List<DefineTimeCron> crons = group.getCrons();
        LOG.info("test size:{}", crons.size());
        for (DefineTimeCron cron : crons) {
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
        List<DefineTimeCron> crons = group.getCrons();
        int size = crons.size();
        LOG.info("test2 size:{}", size);
        for (DefineTimeCron cron : crons) {
            LOG.info("test2 {}", cron.toString());
        }
        assertTrue("*test2*", size == 1);
    }

    @Test
    public void test3() {
        TimeGroupBuilder builder = TimeGroup.builder();
        TimeGroup group = builder.every(3).build();
        List<DefineTimeCron> crons = group.getCrons();
        int size = crons.size();
        LOG.info("test3 size:{}", size);
        for (DefineTimeCron cron : crons) {
            LOG.info("test3 {}", cron.toString());
        }

        assertTrue("*test3*", size == 20);
    }

}
