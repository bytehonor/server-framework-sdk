package com.bytehonor.sdk.server.spring.config;

import java.util.Objects;

import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.getter.StringGetter;
import com.bytehonor.sdk.server.spring.util.LocalEnvUtils;

public class ServerConfig {

    private final String id;

    private final String ip;

    private final String name;

    private final int port;

    private final Environment env;

    private ServerConfig(String id, String ip, String name, int port, Environment env) {
        this.id = id;
        this.ip = ip;
        this.name = name;
        this.port = port;
        this.env = env;
    }

    public static void init(Environment env) {
        Holder.self().init(env);
    }

    public static ServerConfig self() {
        return Holder.self().config;
    }

    private static class Holder {

        private ServerConfig config;

        private Holder() {

        }

        private static class LazyHolder {
            private static Holder SINGLE = new Holder();
        }

        private static Holder self() {
            return LazyHolder.SINGLE;
        }

        private void init(Environment env) {
            Objects.requireNonNull(env, "env");
            String name = StringGetter.optional(env.getProperty("spring.application.name"), "");
            int port = IntegerGetter.optional(env.getProperty("server.port"), 0);
            String id = new StringBuilder().append(name).append("-").append(port).toString();
            String ip = LocalEnvUtils.localIp();
            this.config = new ServerConfig(id, ip, name, port, env);
        }
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
