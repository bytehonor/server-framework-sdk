package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;

public final class LoopTaskFactory {

    private static final Logger LOG = LoggerFactory.getLogger(LoopTaskFactory.class);

    private final List<LoopTask> tasks;

    public LoopTaskFactory() {
        this.tasks = new ArrayList<LoopTask>();
    }

    public List<LoopTask> tasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public LoopTaskFactory add(LoopTask task) {
        Java.requireNonNull(task, "task");

        tasks.add(task);
        return this;
    }

    public void run() {
        try {
            LOG.info("run tasks:{}", tasks.size());
            for (LoopTask task : tasks) {
                task.start();
            }
        } catch (Exception e) {
            LOG.error("run error", e);
        }
    }
}
