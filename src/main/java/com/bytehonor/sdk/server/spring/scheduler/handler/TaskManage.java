package com.bytehonor.sdk.server.spring.scheduler.handler;

public interface TaskManage {

    public void pause(String name);

    public void play(String name);

    public boolean isPause(String name);
}
