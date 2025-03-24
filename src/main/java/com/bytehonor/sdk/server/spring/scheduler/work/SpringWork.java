package com.bytehonor.sdk.server.spring.scheduler.work;

import java.util.List;

/**
 * 一组循环task当成一个work
 * 
 * @author lijianqiang
 */
public interface SpringWork {

    public List<SpringWorkTask> tasks();
}
