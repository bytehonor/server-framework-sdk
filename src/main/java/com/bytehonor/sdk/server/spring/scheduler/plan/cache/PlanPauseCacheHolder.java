package com.bytehonor.sdk.server.spring.scheduler.plan.cache;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlanPauseCacheHolder {

    private static final Set<String> CACHE = new HashSet<String>(1024);

    public static void pause(String name) {
        Objects.requireNonNull(name, "name");

        CACHE.add(name);
    }

    public static void play(String name) {
        Objects.requireNonNull(name, "name");

        CACHE.remove(name);
    }

    public static boolean isPaused(String name) {
        Objects.requireNonNull(name, "name");

        return CACHE.contains(name);
    }
}
