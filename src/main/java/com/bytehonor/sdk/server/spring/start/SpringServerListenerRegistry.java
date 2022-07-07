package com.bytehonor.sdk.server.spring.start;

public class SpringServerListenerRegistry {

    public void register(SpringServerListener listener) {
        SpringServerListenerFactory.register(listener);
    }
}
