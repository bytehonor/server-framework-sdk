package com.bytehonor.sdk.server.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.server.spring.web.controller.ExampleController;
import com.bytehonor.sdk.server.spring.web.controller.SchedulerController;
import com.bytehonor.sdk.server.spring.web.listener.ApplicationReadyEventListener;
import com.bytehonor.sdk.server.spring.web.mvc.ServerWebMvcConfigurer;

import jakarta.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(WebEndpointAutoConfiguration.class)
public class SpringServerAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringServerAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(value = ServerWebMvcConfigurer.class)
    ServerWebMvcConfigurer serverWebMvcConfigurer() {
        LOG.info("[Bytehonor] ServerWebMvcConfigurer");
        return new ServerWebMvcConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean(value = ApplicationReadyEventListener.class)
    ApplicationReadyEventListener applicationReadyEventListener() {
        LOG.info("[Bytehonor] ApplicationReadyEventListener");
        return new ApplicationReadyEventListener();
    }

    @Bean
    @ConditionalOnMissingBean(ExampleController.class)
    ExampleController exampleController() {
        LOG.info("[Bytehonor] ExampleController");
        return new ExampleController();
    }

    @Bean
    @ConditionalOnMissingBean(SchedulerController.class)
    SchedulerController schedulerController() {
        LOG.info("[Bytehonor] SchedulerController");
        return new SchedulerController();
    }
}
