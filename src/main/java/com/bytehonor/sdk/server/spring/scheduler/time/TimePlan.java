package com.bytehonor.sdk.server.spring.scheduler.time;

import java.time.LocalDateTime;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public interface TimePlan {

    public boolean accept(LocalDateTime ldt);

    public SafeTask create(LocalDateTime ldt);
    
    public void print();
}
