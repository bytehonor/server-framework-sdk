package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

/**
 * @author lijianqiang
 *
 */
public interface TimeCron {

    public static final int ANY = -1;

    public boolean match(LocalDateTime ldt);
}
