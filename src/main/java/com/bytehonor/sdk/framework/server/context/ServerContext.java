package com.bytehonor.sdk.framework.server.context;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.getter.IntegerGetter;
import com.bytehonor.sdk.framework.lang.getter.StringGetter;
import com.bytehonor.sdk.framework.lang.util.LocalEnvUtils;

/**
 * 服务节点运行时上下文，保存应用标识与基础网络信息。
 * 
 * @author lijianqiang
 *
 */
public class ServerContext {

    private String id;

    private String ip;

    private String name;

    private int port;

    private Environment env;

    private ServerContext() {
        this.id = "";
        this.ip = "";
        this.name = "";
        this.port = 0;
    }

    private static class LazyHolder {
        private static ServerContext SINGLE = new ServerContext();
    }

    /**
     * 获取单例上下文实例。
     * 
     * @return 上下文单例
     */
    public static ServerContext self() {
        return LazyHolder.SINGLE;
    }

    /**
     * 使用 Spring 环境初始化服务节点信息。
     * 
     * @param env Spring 环境对象
     */
    public static void init(Environment env) {
        Java.requireNonNull(env, "env");
        String name = StringGetter.optional(env.getProperty("spring.application.name"), "");
        int port = IntegerGetter.optional(env.getProperty("server.port"), 0);
        String id = new StringBuilder().append(name).append("-").append(port).toString();
        String ip = LocalEnvUtils.localIp();
        self().name = name;
        self().port = port;
        self().id = id;
        self().ip = ip;
    }

    /**
     * 返回服务节点唯一标识，格式为 name-port。
     * 
     * @return 节点标识
     */
    public String getId() {
        return id;
    }

    /**
     * 返回服务节点 IP。
     * 
     * @return 节点 IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 返回应用名称。
     * 
     * @return 应用名称
     */
    public String getName() {
        return name;
    }

    /**
     * 返回服务端口。
     * 
     * @return 端口号
     */
    public int getPort() {
        return port;
    }

    /**
     * 返回 Spring 环境对象。
     * 
     * @return 环境对象
     */
    public Environment getEnv() {
        return env;
    }

}
