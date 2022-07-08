package com.bytehonor.sdk.server.spring.scheduler.handler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.util.StringObject;
import com.bytehonor.sdk.server.spring.config.ServerConfig;
import com.bytehonor.sdk.server.spring.scheduler.key.SchedulerKeygen;

public abstract class TaskHandler implements TaskLock {

    private static final Logger LOG = LoggerFactory.getLogger(TaskHandler.class);

    private static final String CLAZZ = TaskHandler.class.getSimpleName();

    private final String name;

    public TaskHandler() {
        this.name = name();
    }

    public final boolean accept(LocalDateTime ldt) {
        String key = SchedulerKeygen.make(name, ldt);
        if (lock(key) == false) {
            return false;
        }
        LOG.info("accept:{}", key);
        return true;
    }

    private String name() {
        String name = ServerConfig.self().getName();
        if (StringObject.isEmpty(name) == false) {
            return name;
        }

        name = getClass().getSimpleName();
        if (StringObject.isEmpty(name) == false) {
            return name;
        }

        return CLAZZ;
    }

    public String getName() {
        return name;
    }
}
