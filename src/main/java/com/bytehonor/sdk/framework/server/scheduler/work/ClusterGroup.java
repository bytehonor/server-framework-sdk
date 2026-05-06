package com.bytehonor.sdk.framework.server.scheduler.work;

import com.bytehonor.sdk.framework.lang.Java;

/**
 * 集群任务分组抽象，按主题组织一组 {@link ServerWork}。
 * 
 * @author lijianqiang
 */
public abstract class ClusterGroup {

    private final ServerWorkFactory fatcory;

    public ClusterGroup() {
        this.fatcory = new ServerWorkFactory();
    }

    /**
     * 向当前分组添加一个任务。
     * 
     * @param work 任务实例
     * @return 当前分组实例
     */
    public final ClusterGroup add(ServerWork work) {
        Java.requireNonNull(work, "work");

        fatcory.add(work);
        return this;
    }

    /**
     * 返回当前任务组的主题标识。
     * 
     * @return 主题标识
     */
    public abstract String subject();

    /**
     * 启动当前分组内的全部任务。
     */
    public final void start() {
        this.fatcory.play();
    }
}
