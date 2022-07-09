package com.bytehonor.sdk.server.spring.config;

import java.util.Objects;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.StringGetter;
import com.bytehonor.sdk.server.spring.util.LocalEnvUtils;

public class ServerConfig {

    private String id;

    private String ip;

    private String name;

    private int port;

    private Environment env;

    private ServerConfig() {
        this.port = 0;
    }

    private static class LazyHolder {
        private static ServerConfig SINGLE = new ServerConfig();
    }

    public static ServerConfig self() {
        return LazyHolder.SINGLE;
    }

    public static void init(Environment env) {
        Objects.requireNonNull(env, "env");
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
