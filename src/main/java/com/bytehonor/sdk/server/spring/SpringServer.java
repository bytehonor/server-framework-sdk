package com.bytehonor.sdk.server.spring;

import java.util.Objects;

import com.bytehonor.sdk.server.spring.config.ServerConfig;
import com.bytehonor.sdk.server.spring.context.ApplicationContextHolder;

public class SpringServer {

    public static <T> T getBean(Class<T> requiredType) {
        Objects.requireNonNull(requiredType, "requiredType");

        return ApplicationContextHolder.getBean(requiredType);
    }

    public static String id() {
        return ServerConfig.self().getId();
    }

    public static String ip() {
        return ServerConfig.self().getIp();
    }

    public static String name() {
        return ServerConfig.self().getName();
    }

    public static int port() {
        return ServerConfig.self().getPort();
    }

    public static String getProperty(String key) {
        return ServerConfig.self().getEnv().getProperty(key);
    }
}
