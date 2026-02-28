package com.bytehonor.sdk.framework.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.thread.SafeTask;
import com.bytehonor.sdk.framework.lang.thread.Sleep;
import com.bytehonor.sdk.framework.lang.thread.SpringTaskPoolExecutor;
import com.bytehonor.sdk.framework.server.SpringServer;

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

        LOG.info("context, id:{}", context.getId());

        SpringServer.init(context);
        
        LOG.info("server, id:{}, ip:{}", SpringServer.id(), SpringServer.ip());

        SpringTaskPoolExecutor.put(new SafeTask() {
            
            @Override
            public void handle() {
                SpringStarter starter = SpringServer.bean(SpringStarter.class);
                if (starter == null) {
                    LOG.warn("SpringStarter null");
                    return;
                }
                
                Sleep.millis(500L);

                try {
                    starter.start();
                } catch (Exception e) {
                    LOG.error("start error", e);
                }
            }
            
        });
    }
}
