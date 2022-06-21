package com.bytehonor.sdk.server.spring.thread;

import com.bytehonor.sdk.lang.bytehonor.task.WhileBlockTask;

public class LinkedBlockingTask<T> extends WhileBlockTask {

    private final QueueBlockingProducer<T> producer;
    private final QueueConsumer<T> consumer;

    public LinkedBlockingTask(QueueBlockingProducer<T> producer, QueueConsumer<T> consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public final void runThenBlock() throws InterruptedException {
        T t = producer.produce();
        consumer.consume(t);
    }
}
