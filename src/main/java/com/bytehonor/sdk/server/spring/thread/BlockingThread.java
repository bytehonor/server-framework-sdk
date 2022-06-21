package com.bytehonor.sdk.server.spring.thread;

import java.util.concurrent.LinkedBlockingQueue;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.lang.bytehonor.task.WhileBlockTask;

public class BlockingThread<T> {

    private final LinkedBlockingQueue<T> queue;

    private final Thread thread;

    private BlockingThread(WhileBlockTask task, int queueSize) {
        queue = new LinkedBlockingQueue<T>(queueSize);
        thread = new Thread(task);
    }

    public static <T> BlockingThread<T> create(WhileBlockTask task, int queueSize, String name) {
        BlockingThread<T> bt = new BlockingThread<T>(task, queueSize);
        if (StringObject.isEmpty(name) == false) {
            bt.thread.setName(name);
        }
        return bt;
    }

    public void start() {
        this.thread.start();
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
