package com.bytehonor.sdk.server.spring.work;

public interface SubjectWork {

    /**
     * 主题
     * 
     * @return
     */
    public String subject();

    /**
     * 启动，只启动一次
     */
    public void start();
}
