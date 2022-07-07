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
        return ServerConfig.id();
    }

    public static String ip() {
        return ServerConfig.ip();
    }

    public static String name() {
        return ServerConfig.name();
    }

    public static int port() {
        return ServerConfig.port();
    }

    public static String getProperty(String key) {
        return ServerConfig.getProperty(key);
    }
}
