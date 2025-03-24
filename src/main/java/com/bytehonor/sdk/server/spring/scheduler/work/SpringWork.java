package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.List;

/**
 * 简单的主题工作
 */
public interface SpringWork {

    public List<SpringWorkTask> tasks();
}
