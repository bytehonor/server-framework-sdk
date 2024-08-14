package com.bytehonor.sdk.server.spring.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

/**
 * 升级的主题工作，循环执行
 */
public abstract class SubjectTask extends SafeTask implements SubjectWork {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectTask.class);

    /**
     * 主题
     * 
     * @return
     */
    public abstract String subject();

    /**
     * 循环间隔毫秒数
     * 
     * @return
     */
    public abstract long intervalMillis();

    public final void start() {
        long intervals = intervalMillis();
        LOG.info("start subject:{}, intervals:{}", subject(), intervals);
        ScheduleTaskPoolExecutor.schedule(this, 100L, intervals);
    }

}
