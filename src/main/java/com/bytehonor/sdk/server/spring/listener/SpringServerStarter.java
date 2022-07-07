package com.bytehonor.sdk.server.spring.listener;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.bytehonor.sdk.server.spring.config.ServerConfig;
import com.bytehonor.sdk.server.spring.context.ApplicationContextHolder;

public class SpringServerStarter {

    public static void init(ConfigurableApplicationContext context) {
        ApplicationContextHolder.setContext(context);

        ServerConfig.init(ApplicationContextHolder.getBean(Environment.class));

        SpringServerListener listener = ApplicationContextHolder.getBean(SpringServerListener.class);
        if (listener != null) {
            listener.onStart();
        }
    }
}
