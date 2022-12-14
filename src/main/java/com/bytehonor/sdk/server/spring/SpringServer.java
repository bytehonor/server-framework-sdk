package com.bytehonor.sdk.server.spring;

import java.util.Objects;

import com.bytehonor.sdk.server.spring.context.ApplicationContextHolder;
import com.bytehonor.sdk.server.spring.context.ServerContext;

/**
 * @author lijianqiang
 *
 */
public class SpringServer {

    public static <T> T bean(Class<T> requiredType) {
        Objects.requireNonNull(requiredType, "requiredType");

        return ApplicationContextHolder.getBean(requiredType);
    }

    public static String id() {
        return ServerContext.me().getId();
    }

    public static String ip() {
        return ServerContext.me().getIp();
    }

    public static String name() {
        return ServerContext.me().getName();
    }

    public static int port() {
        return ServerContext.me().getPort();
    }

    public static String property(String key) {
        Objects.requireNonNull(key, "key");

        return ServerContext.me().getEnv().getProperty(key);
    }
}
