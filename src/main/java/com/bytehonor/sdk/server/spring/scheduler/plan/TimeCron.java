package com.bytehonor.sdk.server.spring.scheduler.plan;

import java.time.LocalDateTime;

/**
 * @author lijianqiang
 *
 */
public interface TimeCron {

    public boolean match(LocalDateTime ldt);
}
