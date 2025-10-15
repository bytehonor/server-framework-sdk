package com.bytehonor.sdk.framework.server.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.framework.lang.thread.SafeTask;

/**
 * @author lijianqiang
 *
 */
public interface SpringPlan {

    public boolean accept(LocalDateTime ldt);

    public SafeTask create(LocalDateTime ldt);
    
    public void print();
}
