package com.bytehonor.sdk.server.spring.thread;

public interface QueueProducer<T> {

    public T produce();
}
