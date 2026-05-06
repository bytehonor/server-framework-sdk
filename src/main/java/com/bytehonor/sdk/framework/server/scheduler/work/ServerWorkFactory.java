package com.bytehonor.sdk.framework.server.scheduler.work;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;

/**
 * 维护并启动 {@link ServerWork} 任务集合的工厂类。
 * 
 * @author lijianqiang
 */
public final class ServerWorkFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ServerWorkFactory.class);

    private final List<ServerWork> works;

    public ServerWorkFactory() {
        this.works = new ArrayList<ServerWork>();
    }

    /**
     * 返回当前已注册任务列表。
     * 
     * @return 任务列表
     */
    public List<ServerWork> works() {
        return works;
    }

    /**
     * 判断是否未注册任何任务。
     * 
     * @return true 表示为空
     */
    public boolean isEmpty() {
        return works.isEmpty();
    }

    /**
     * 添加一个待调度任务。
     * 
     * @param work 任务实例
     * @return 当前工厂，便于链式调用
     */
    public ServerWorkFactory add(ServerWork work) {
        Java.requireNonNull(work, "work");

        works.add(work);
        return this;
    }

    /**
     * 启动全部已注册任务。
     */
    public void play() {
        try {
            LOG.info("play works:{}", works.size());
            for (ServerWork work : works) {
                work.schedule();
            }
        } catch (Exception e) {
            LOG.error("play error", e);
        }
    }
}
