package com.bytehonor.sdk.server.spring.thread;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedBlockingThread<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedBlockingThread.class);

    private final LinkedBlockingTask<T> task;
    private final Thread thread;

    private LinkedBlockingThread(LinkedBlockingTask<T> task) {
        this.task = task;
        this.thread = new Thread(task);
    }

    public static <T> LinkedBlockingThread<T> create(LinkedBlockingTask<T> task, String name) {
        Objects.requireNonNull(task, "task");
        Objects.requireNonNull(name, "name");

        LinkedBlockingThread<T> bt = new LinkedBlockingThread<T>(task);
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
        this.task.add(t);
    }
}
