package com.bytehonor.sdk.framework.server.scheduler.plan.cache;

import java.util.HashSet;
import java.util.Set;

import com.bytehonor.sdk.framework.lang.Java;

public class SpringPlanPauseCache {

    private static final Set<String> CACHE = new HashSet<String>(1024);

    public static void pause(String name) {
        Java.requireNonNull(name, "name");

        CACHE.add(name);
    }

    public static void play(String name) {
        Java.requireNonNull(name, "name");

        CACHE.remove(name);
    }

    public static boolean isPaused(String name) {
        Java.requireNonNull(name, "name");

        return CACHE.contains(name);
    }
}
