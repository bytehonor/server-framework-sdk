package com.bytehonor.sdk.server.spring.pool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.SpringTaskExecutor;

public class SpringTaskExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTaskExecutorTest.class);

    @Test
    public void test() {
        SpringTaskExecutor.put(new SafeTask() {

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

        SpringTaskExecutor.put(new SafeTask() {

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
