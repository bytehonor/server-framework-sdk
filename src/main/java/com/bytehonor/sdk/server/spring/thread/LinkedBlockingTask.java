package com.bytehonor.sdk.server.spring.thread;

import java.util.concurrent.LinkedBlockingQueue;

import com.bytehonor.sdk.lang.bytehonor.task.WhileBlockTask;

public abstract class LinkedBlockingTask<T> extends WhileBlockTask {

    private final LinkedBlockingQueue<T> queue;

    public LinkedBlockingTask(int queueSize) {
        queue = new LinkedBlockingQueue<T>(queueSize);
    }

    @Override
    public final void runThenBlock() throws InterruptedException {
        T t = take();
        process(t);
    }

    public abstract void process(T t);

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
