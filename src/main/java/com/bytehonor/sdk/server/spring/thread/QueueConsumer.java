package com.bytehonor.sdk.server.spring.thread;

public interface QueueConsumer<T> {

    public void consume(T payload);
}
