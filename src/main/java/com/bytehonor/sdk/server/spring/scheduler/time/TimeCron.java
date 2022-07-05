package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

/**
 * @author lijianqiang
 *
 */
public interface TimeCron {

    public boolean match(LocalDateTime ldt);
}
