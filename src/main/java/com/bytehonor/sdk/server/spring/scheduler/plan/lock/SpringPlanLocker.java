package com.bytehonor.sdk.server.spring.scheduler.plan.lock;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.server.spring.web.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public abstract class SpringPlanLocker {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlanLocker.class);

    private static final String CLAZZ = SpringPlanLocker.class.getSimpleName();

    private final String name;

    public SpringPlanLocker() {
        this.name = name();
    }

    public final boolean accept(LocalDateTime ldt) {
        Java.requireNonNull(ldt, "ldt");

        String key = SpringPlanKeygen.make(name, ldt);
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
