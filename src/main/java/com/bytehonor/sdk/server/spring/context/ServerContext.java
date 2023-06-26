package com.bytehonor.sdk.server.spring.context;

import java.util.Objects;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.StringGetter;
import com.bytehonor.sdk.lang.spring.util.LocalEnvUtils;

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

    public static ServerContext me() {
        return LazyHolder.SINGLE;
    }

    public static void init(Environment env) {
        Objects.requireNonNull(env, "env");
        String name = StringGetter.optional(env.getProperty("spring.application.name"), "");
        int port = IntegerGetter.optional(env.getProperty("server.port"), 0);
        String id = new StringBuilder().append(name).append("-").append(port).toString();
        String ip = LocalEnvUtils.localIp();
        me().name = name;
        me().port = port;
        me().id = id;
        me().ip = ip;
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
