package com.bytehonor.sdk.server.spring.scheduler.work;

/**
 * 简单的主题工作，start方法仅执行一次
 */
public interface SpringWork {

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
