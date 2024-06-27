package com.bytehonor.sdk.server.spring.web.context;

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

    private static ApplicationContextHolder me() {
        return LazyHolder.SINGLE;
    }

    public static ConfigurableApplicationContext getContext() {
        return me().applicationContext;
    }

    public static void setContext(ConfigurableApplicationContext context) {
        me().applicationContext = context;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (me().applicationContext == null) {
            throw new RuntimeException("applicationContext null");
        }
        return me().applicationContext.getBean(requiredType);
    }

    public static boolean inited() {
        return me().applicationContext != null;
    }
}
