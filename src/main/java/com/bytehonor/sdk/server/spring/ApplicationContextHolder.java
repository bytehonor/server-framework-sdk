package com.bytehonor.sdk.server.spring;

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

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static ConfigurableApplicationContext getContext() {
        return self().getApplicationContext();
    }

    public static void setContext(ConfigurableApplicationContext applicationContext) {
        self().setApplicationContext(applicationContext);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return self().getApplicationContext().getBean(requiredType);
    }

    public static boolean inited() {
        return self().applicationContext != null;
    }
}
