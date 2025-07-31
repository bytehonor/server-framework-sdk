package com.bytehonor.sdk.server.spring.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.SpringServer;
import com.bytehonor.sdk.server.spring.exception.ErrorConvertor;
import com.bytehonor.sdk.server.spring.web.context.ApplicationContextHolder;
import com.bytehonor.sdk.server.spring.web.context.ServerContext;

/**
 * 服务初始化动作
 * 
 * @author lijianqiang
 *
 */
public class ApplicationReadyHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationReadyHandler.class);

    public static void init(ConfigurableApplicationContext context) {
        Java.requireNonNull(context, "context");

        LOG.info("context, id:{}", context.getId());

        ApplicationContextHolder.setContext(context);

        ServerContext.init(ApplicationContextHolder.getBean(Environment.class));

        try {
            SpringStarter starter = SpringServer.bean(SpringStarter.class);
            if (starter != null) {
                starter.onStart();
            } else {
                LOG.warn("SpringStarter null");
            }
        } catch (Exception e) {
            LOG.warn("SpringStarter:{}", ErrorConvertor.format(e));
        }
    }
}
