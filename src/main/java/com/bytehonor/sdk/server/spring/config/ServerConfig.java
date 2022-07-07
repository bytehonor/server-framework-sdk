package com.bytehonor.sdk.server.spring.config;

import java.util.Objects;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.StringGetter;

public class ServerConfig {

    private String id;

    private String name;

    private int port;

    private Environment env;

    private ServerConfig() {
        this.id = "";
        this.name = "";
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
        // Environment env = ApplicationContextHolder.getBean(Environment.class);
        String name = StringGetter.optional(env.getProperty("spring.application.name"), "");
        int port = IntegerGetter.optional(env.getProperty("server.port"), 0);
        self().name = name;
        self().port = port;
        self().id = new StringBuilder().append(name).append("-").append(port).toString();
    }

    public static String id() {
        return self().id;
    }

    public static String name() {
        return self().name;
    }

    public static int port() {
        return self().port;
    }

    public static String getProperty(String key) {
        Objects.requireNonNull(key, "key");

        return self().env.getProperty(key);
    }
}
