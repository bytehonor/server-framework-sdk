package com.bytehonor.sdk.framework.server.scheduler.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;

/**
 * 循环执行的任务
 */
public abstract class ServerWork extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWork.class);

    /**
     * 循环间隔毫秒数
     * 
     * 小于1则不循环，仅执行一次
     * 
     * @return
     */
    public abstract long intervals();

    public final void schedule() {
        long intervals = intervals();
        LOG.info("start {}, intervals:{}", thisName(), intervals);

        if (intervals < 1L) {
            ScheduleTaskPoolExecutor.schedule(this, 100L);
        } else {
            ScheduleTaskPoolExecutor.schedule(this, 100L, intervals);
        }
    }

    private String thisName() {
        String name = this.getClass().getSimpleName();
        if (StringKit.isEmpty(name)) {
            name = "Anonymous";
        }
        return name;
    }
}
