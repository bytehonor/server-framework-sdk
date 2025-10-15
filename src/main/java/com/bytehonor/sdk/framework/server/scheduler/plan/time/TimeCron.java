package com.bytehonor.sdk.framework.server.scheduler.plan.time;

import java.time.LocalDateTime;

/**
 * @author lijianqiang
 *
 */
public interface TimeCron {

    public boolean match(LocalDateTime ldt);
}
