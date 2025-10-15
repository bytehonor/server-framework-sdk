package com.bytehonor.sdk.framework.server.scheduler.task;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.thread.SafeTask;

public class PrintTimeTask extends SafeTask {

    private static final Logger LOG = LoggerFactory.getLogger(PrintTimeTask.class);

    private final LocalDateTime ldt;

    public PrintTimeTask(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    @Override
    public void handle() {
        LOG.info("ldt:{}", ldt);
    }

}
