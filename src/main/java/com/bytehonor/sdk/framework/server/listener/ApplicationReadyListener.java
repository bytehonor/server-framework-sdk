package com.bytehonor.sdk.framework.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * <pre>
 *  event order:  
 *    ApplicationStartingEvent -> ApplicationEnvironmentPreparedEvent 
 *       -> ApplicationPreparedEvent -> ApplicationStartedEvent -> ApplicationReadyEvent -> ApplicationFailedEvent
 * </pre>
 * 
 * @author lijianqiang
 *
 */
//@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationReadyListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        LOG.info("onApplicationEvent begin");

        ApplicationReadyHandler.handle(event.getApplicationContext());

        LOG.info("onApplicationEvent end");
    }

}
