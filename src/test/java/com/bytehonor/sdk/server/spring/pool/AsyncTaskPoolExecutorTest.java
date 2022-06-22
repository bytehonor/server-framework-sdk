package com.bytehonor.sdk.server.spring.pool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.beautify.lang.thread.ThreadTaskExecutor;
import com.bytehonor.sdk.define.bytehonor.task.SafeTask;

public class AsyncTaskPoolExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncTaskPoolExecutorTest.class);

    @Test
    public void test() {
        ThreadTaskExecutor.put(new SafeTask() {

            @Override
            public void runInSafe() {
                LOG.info("111");
            }

        });

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            LOG.error("sleep", e);
        }

        ThreadTaskExecutor.put(new SafeTask() {

            @Override
            public void runInSafe() {
                LOG.info("222");
            }

        });

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            LOG.error("sleep", e);
        }
    }

}
