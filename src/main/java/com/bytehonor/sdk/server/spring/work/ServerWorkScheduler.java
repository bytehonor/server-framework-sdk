package com.bytehonor.sdk.server.spring.work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * 本地承担任务，有多少任务启动多少
 * 
 * @author lijianqiang
 *
 */
public class ServerWorkScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkScheduler.class);

    private static final long DELAYS = TimeConstants.SECOND * 3;
    private static final long INTERVALS = TimeConstants.MINUTE;

    private final long delayMillis;
    private final long intervalMillis;

    private final List<SubjectWork> works;
    private final Set<String> subjects;

    public ServerWorkScheduler() {
        this(DELAYS, INTERVALS);
    }

    public ServerWorkScheduler(long delayMillis, long intervalMillis) {
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
        this.works = new ArrayList<SubjectWork>();
        this.subjects = new HashSet<String>();
    }

    public void start() {
        if (CollectionUtils.isEmpty(works)) {
            LOG.warn("works empty");
            return;
        }

        ScheduleWorkPoolExecutor.schedule(new SafeTask() {

            @Override
            public void runInSafe() {
                doWork();
            }

        }, delayMillis, intervalMillis);
    }

    public ServerWorkScheduler add(SubjectWork work) {
        Objects.requireNonNull(work, "work");

        LOG.info("subject:{}", work.subject());

        if (SpringString.isEmpty(work.subject()) == false) {
            works.add(work);
        }

        return this;
    }

    private void doWork() {
        if (CollectionUtils.isEmpty(subjects) == false) {
            return;
        }

        LOG.info("begin works:{}", works.size());

        try {
            for (SubjectWork work : works) {
                subjects.add(work.subject());
                work.start();
                LOG.info("start done, subject:{}", work.subject());
            }
        } catch (Exception e) {
            LOG.error("run error", e);
        }
    }
}
