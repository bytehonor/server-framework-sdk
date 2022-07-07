package com.bytehonor.sdk.server.spring.start;

public interface SpringServerListener {

    public void register(SpringServerListenerRegistry registry);

    public void onStart();
}
