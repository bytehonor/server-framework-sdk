package com.bytehonor.sdk.server.spring.scheduler.task;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;

public class PrintTimeTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(PrintTimeTask.class);

    private final LocalDateTime ldt;

    public PrintTimeTask(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    @Override
    public void runInSafe() {
        LOG.info("ldt:{}", ldt);
    }

}
