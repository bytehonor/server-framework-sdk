package com.bytehonor.sdk.server.spring.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.LoopIntervalTask;
import com.google.common.base.Objects;

/**
 * 竞争任务
 * 
 * @author lijianqiang
 *
 */
public class SpringJobTask extends LoopIntervalTask {

    private static final Logger LOG = LoggerFactory.getLogger(SpringJobThread.class);

    private final String name;

    private final SpringJobLocker locker;

    private final SpringJobConfig config;

    private final List<SpringJob> jobs;

    private String target;

    public SpringJobTask(String name, SpringJobLocker locker, SpringJobConfig config, List<SpringJob> jobs) {
        this.target = "";
        this.name = name;
        this.locker = locker;
        this.config = config;
        this.jobs = jobs;
    }

    @Override
    public long delays() {
        return config.getTaskDelayMillis();
    }

    @Override
    public long intervals() {
        return config.getTaskIntervalMillis();
    }

    @Override
    public void runThenSleep() {
        try {
            doCompete();
            doKeep();
            doCheck();
        } catch (Exception e) {
            LOG.error("doRun error", e);
        }
    }

    private void doCompete() {
        if (SpringString.isEmpty(target) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(jobs)) {
            LOG.info("missions empty");
            return;
        }

        for (SpringJob mission : jobs) {
            if (locker.lock(mission.target(), name, config.getTaskLockMillis()) == false) {
                continue;
            }

            target = mission.target();
            mission.start();
            LOG.info("doCompete target:{}, name:{}", target, name);
            break;
        }
    }

    private void doKeep() {
        if (SpringString.isEmpty(target)) {
            LOG.info("doKeep target null, name:{}", name);
            return;
        }

        String val = locker.get(target);
        boolean success = Objects.equal(val, name);
        LOG.info("doKeep success:{}, target:{}, name:{}", success, target, name);
        if (success) {
            locker.expireAt(target, System.currentTimeMillis() + config.getTaskLockMillis());
        }
    }

    private void doCheck() {
        if (CollectionUtils.isEmpty(jobs)) {
            return;
        }

        for (SpringJob job : jobs) {
            if (SpringString.isEmpty(locker.get(job.target()))) {
                LOG.warn("doCheck target:{} no runner", job.target());
            }
        }
    }
}
