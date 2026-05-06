package com.bytehonor.sdk.framework.server.scheduler.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.ScheduleTaskPoolExecutor;

/**
 * 可被调度线程池循环执行的任务基类。
 * 
 * @author lijianqiang
 */
public abstract class ServerWork extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWork.class);

    /**
     * 返回任务循环间隔（毫秒）。
     * 
     * 小于 1 表示仅执行一次，不进行周期调度。
     * 
     * @return 循环间隔毫秒数
     */
    public abstract long intervals();

    /**
     * 根据 {@link #intervals()} 的返回值注册调度任务。
     */
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
