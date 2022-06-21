package com.bytehonor.sdk.server.spring.thread;

public interface QueueBlockingProducer<T> {

    public T produce() throws InterruptedException;
}
