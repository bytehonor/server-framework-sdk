package com.bytehonor.sdk.server.spring.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.bytehonor.sdk.lang.spring.Java;
import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.lang.spring.thread.Sleep;
import com.bytehonor.sdk.lang.spring.thread.SpringTaskPoolExecutor;
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

        LOG.info("context, id:{}", context.getId());

        SpringServer.init(context);
        
        LOG.info("server, id:{}, ip:{}", SpringServer.id(), SpringServer.ip());

        SpringTaskPoolExecutor.put(new SafeTask() {
            
            @Override
            public void runInSafe() {
                
                Sleep.millis(500L);
                
                SpringStarter starter = SpringServer.bean(SpringStarter.class);
                if (starter == null) {
                    LOG.warn("SpringStarter null");
                    return;
                }
                
                try {
                    starter.start();
                } catch (Exception e) {
                    LOG.warn("SpringStarter:{}", ErrorConvertor.format(e));
                }
            }
            
        });
    }
}
