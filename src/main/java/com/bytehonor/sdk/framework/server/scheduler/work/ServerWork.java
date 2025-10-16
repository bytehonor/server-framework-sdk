package com.bytehonor.sdk.framework.server.scheduler.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.exception.SpringServerException;

/**
 * 循环执行的任务
 */
public abstract class ServerWork extends SafeTask  {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWork.class);

    /**
     * 循环间隔毫秒数
     * 
     * @return
     */
    public abstract long intervals();

    public final void start() {
        long intervals = intervals();
        LOG.info("start {}, intervals:{}", thisName(), intervals);
        
        if (intervals < 1L) {
            throw new SpringServerException("invalid intervals:" + intervals);
        }
        
        ScheduleTaskPoolExecutor.schedule(this, 100L, intervals);
    }

    private String thisName() {
        String name = this.getClass().getSimpleName();
        if (StringKit.isEmpty(name)) {
            name = "Anonymous";
        }
        return name;
    }
}
