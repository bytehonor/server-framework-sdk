package com.bytehonor.sdk.server.spring.listener;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.bytehonor.sdk.server.spring.context.ApplicationContextHolder;
import com.bytehonor.sdk.server.spring.context.ServerContext;

/**
 * 服务初始化动作
 * 
 * @author lijianqiang
 *
 */
public class SpringServerStarter {

    private static final Logger LOG = LoggerFactory.getLogger(SpringServerStarter.class);

    public static void init(ConfigurableApplicationContext context) {
        Objects.requireNonNull(context, "context");

        LOG.info("context, id:{}, name:{}", context.getId(), context.getApplicationName());

        ApplicationContextHolder.setContext(context);

        ServerContext.init(ApplicationContextHolder.getBean(Environment.class));

        SpringServerListener listener = ApplicationContextHolder.getBean(SpringServerListener.class);
        if (listener != null) {
            listener.onStart();
        } else {
            LOG.warn("no SpringServerListener");
        }
    }
}
