package com.bytehonor.sdk.server.spring.start;

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
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        LOG.info("** ApplicationReadyEventListener begin");

        SpringServerStarter.init(event.getApplicationContext());

        LOG.info("** ApplicationReadyEventListener end");
    }

}
