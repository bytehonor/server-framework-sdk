package com.bytehonor.sdk.framework.server.context;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.getter.IntegerGetter;
import com.bytehonor.sdk.framework.lang.getter.StringGetter;
import com.bytehonor.sdk.framework.lang.util.LocalEnvUtils;

/**
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

    public static ServerContext self() {
        return LazyHolder.SINGLE;
    }

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

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public Environment getEnv() {
        return env;
    }

}
