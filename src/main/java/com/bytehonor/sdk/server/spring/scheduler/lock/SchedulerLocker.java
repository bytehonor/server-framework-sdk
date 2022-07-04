package com.bytehonor.sdk.server.spring.scheduler.lock;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.util.StringObject;
import com.bytehonor.sdk.server.spring.ApplicationContextHolder;
import com.bytehonor.sdk.server.spring.scheduler.key.SchedulerKeygen;

public abstract class SchedulerLocker {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerLocker.class);

    private final String name;

    public SchedulerLocker() {
        this.name = name();
    }

    public final boolean accept(LocalDateTime ldt) {
        String key = SchedulerKeygen.make(name, ldt);
        if (lock(key) == false) {
            return false;
        }
        LOG.info("key:{}", key);
        return true;
    }

    private String name() {
        String name = "";
        if (ApplicationContextHolder.inited()) {
            Environment env = ApplicationContextHolder.getBean(Environment.class);
            name = env.getProperty("spring.application.name");
        }
        if (StringObject.isEmpty(name)) {
            name = getClass().getSimpleName();
        }
        if (StringObject.isEmpty(name)) {
            name = SchedulerLocker.class.getSimpleName();
        }
        return name;
    }

    public abstract boolean lock(String key);

    public String getName() {
        return name;
    }

}
