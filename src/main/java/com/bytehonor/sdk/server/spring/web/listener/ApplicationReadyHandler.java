package com.bytehonor.sdk.server.spring.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.server.spring.SpringServer;
import com.bytehonor.sdk.server.spring.exception.ErrorConvertor;

/**
 * 服务初始化动作
 * 
 * @author lijianqiang
 *
 */
public class ApplicationReadyHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationReadyHandler.class);

    public static void handle(ConfigurableApplicationContext context) {
        Java.requireNonNull(context, "context");

        // LOG.info("context, id:{}", context.getId());
        
        SpringServer.init(context);
        LOG.info("server, id:{}", SpringServer.id());
        
        try {
            SpringStarter starter = SpringServer.bean(SpringStarter.class);
            if (starter != null) {
                starter.start();
            } else {
                LOG.warn("SpringStarter null");
            }
        } catch (Exception e) {
            LOG.warn("SpringStarter:{}", ErrorConvertor.format(e));
        }
    }
}
