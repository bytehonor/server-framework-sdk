package com.bytehonor.sdk.server.spring.scheduler.lock;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.util.StringObject;
import com.bytehonor.sdk.server.spring.context.ServerContext;
import com.bytehonor.sdk.server.spring.scheduler.key.SchedulerKeygen;

public abstract class TaskLocker {

    private static final Logger LOG = LoggerFactory.getLogger(TaskLocker.class);

    private static final String CLAZZ = TaskLocker.class.getSimpleName();

    private final String name;

    public TaskLocker() {
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
        String name = ServerContext.self().getName();
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

    public abstract boolean lock(String key);
}
