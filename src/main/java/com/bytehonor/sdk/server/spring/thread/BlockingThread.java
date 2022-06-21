package com.bytehonor.sdk.server.spring.thread;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.bytehonor.task.WhileBlockTask;

public class BlockingThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BlockingThread.class);

    private final LinkedBlockingQueue<T> queue;

    private final Thread thread;

    private BlockingThread(WhileBlockTask task, int queueSize) {
        queue = new LinkedBlockingQueue<T>(queueSize);
        thread = new Thread(task);
    }

    public static <T> BlockingThread<T> create(WhileBlockTask task, int queueSize, String name) {
        Objects.requireNonNull(task, "task");
        Objects.requireNonNull(name, "name");

        BlockingThread<T> bt = new BlockingThread<T>(task, queueSize);
        bt.thread.setName(name);
        return bt;
    }

    public void start() {
        this.thread.start();
        LOG.info("[{}] start", thread.getName());
    }

    public void add(T t) {
        if (t == null) {
            return;
        }
        this.queue.add(t);
    }

    public T take() throws InterruptedException {
        return this.queue.take();
    }
}
