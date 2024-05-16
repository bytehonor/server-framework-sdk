package com.bytehonor.sdk.server.spring.config;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.expose.EndpointExposure;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.server.spring.listener.ApplicationReadyEventListener;
import com.bytehonor.sdk.server.spring.scheduler.constant.ServerEndpointConstants;
import com.bytehonor.sdk.server.spring.scheduler.controller.SchedulerControllerEndpoint;
import com.bytehonor.sdk.server.spring.web.mvc.ServerWebMvcConfigurer;

import jakarta.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@EnableConfigurationProperties({ WebEndpointProperties.class })
@AutoConfigureAfter(WebEndpointAutoConfiguration.class)
public class ServerCommonConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerCommonConfiguration.class);

    public ServerCommonConfiguration(WebEndpointProperties webEndpointProperties) {
        this.initWebEndpoint(webEndpointProperties);
    }

    private void initWebEndpoint(WebEndpointProperties webEndpointProperties) {
        Set<String> include = webEndpointProperties.getExposure().getInclude();
        include.add(ServerEndpointConstants.SCHEDULER_ENDPOINT);
        String[] defaults = EndpointExposure.WEB.getDefaultIncludes();
        for (String def : defaults) {
            include.add(def);
        }
    }

    @Bean
    @ConditionalOnMissingBean(value = ServerWebMvcConfigurer.class)
    ServerWebMvcConfigurer serverWebMvcConfigurer() {
        LOG.info("[Bytehonor] ServerWebMvcConfigurer");
        return new ServerWebMvcConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean(value = SchedulerControllerEndpoint.class)
    SchedulerControllerEndpoint schedulerControllerEndpoint() {
        LOG.info("[Bytehonor] SchedulerControllerEndpoint");
        return new SchedulerControllerEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = ApplicationReadyEventListener.class)
    ApplicationReadyEventListener applicationReadyEventListener() {
        LOG.info("[Bytehonor] ApplicationReadyEventListener");
        return new ApplicationReadyEventListener();
    }

}
