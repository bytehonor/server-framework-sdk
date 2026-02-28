package com.bytehonor.sdk.framework.server.scheduler.plan.lock;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.server.context.ServerContext;

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
        if (StringKit.isEmpty(name) == false) {
            return name;
        }

        name = getClass().getSimpleName();
        if (StringKit.isEmpty(name) == false) {
            return name;
        }

        return CLAZZ;
    }

    public String getName() {
        return name;
    }

    public abstract boolean lock(String key);
}
