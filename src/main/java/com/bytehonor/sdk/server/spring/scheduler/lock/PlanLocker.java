package com.bytehonor.sdk.server.spring.scheduler.lock;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.server.spring.scheduler.key.SchedulerKeygen;
import com.bytehonor.sdk.server.spring.web.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public abstract class PlanLocker {

    private static final Logger LOG = LoggerFactory.getLogger(PlanLocker.class);

    private static final String CLAZZ = PlanLocker.class.getSimpleName();

    private final String name;

    public PlanLocker() {
        this.name = name();
    }

    public final boolean accept(LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");

        String key = SchedulerKeygen.make(name, ldt);
        if (lock(key) == false) {
            return false;
        }
        LOG.info("accept:{}", key);
        return true;
    }

    private String name() {
        String name = ServerContext.self().getName();
        if (SpringString.isEmpty(name) == false) {
            return name;
        }

        name = getClass().getSimpleName();
        if (SpringString.isEmpty(name) == false) {
            return name;
        }

        return CLAZZ;
    }

    public String getName() {
        return name;
    }

    public abstract boolean lock(String key);
}
