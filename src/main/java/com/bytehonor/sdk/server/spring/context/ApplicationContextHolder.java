package com.bytehonor.sdk.server.spring.context;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lijianqiang
 *
 */
public class ApplicationContextHolder {

    private ConfigurableApplicationContext applicationContext;

    private ApplicationContextHolder() {

    }

    private static class LazyHolder {
        private static ApplicationContextHolder SINGLE = new ApplicationContextHolder();
    }

    private static ApplicationContextHolder self() {
        return LazyHolder.SINGLE;
    }

    public static ConfigurableApplicationContext getContext() {
        return self().applicationContext;
    }

    public static void setContext(ConfigurableApplicationContext context) {
        self().applicationContext = context;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (self().applicationContext == null) {
            throw new RuntimeException("applicationContext null");
        }
        return self().applicationContext.getBean(requiredType);
    }

    public static boolean inited() {
        return self().applicationContext != null;
    }
}
