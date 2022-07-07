package com.bytehonor.sdk.server.spring.config;

import java.util.Set;

import javax.servlet.Servlet;

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

import com.bytehonor.sdk.server.spring.constant.SpringServerConstants;
import com.bytehonor.sdk.server.spring.scheduler.controller.SchedulerControllerEndpoint;
import com.bytehonor.sdk.server.spring.start.ApplicationReadyEventListener;
import com.bytehonor.sdk.server.spring.web.advisor.ErrorResponseAdvisor;
import com.bytehonor.sdk.server.spring.web.advisor.JsonResponseAdvisor;
import com.bytehonor.sdk.server.spring.web.mvc.ServerWebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(WebEndpointAutoConfiguration.class)
@EnableConfigurationProperties({ SpringBootStandardProperties.class, WebEndpointProperties.class })
public class SpringBootStandardConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootStandardConfiguration.class);

    private final WebEndpointProperties webEndpointProperties;

    public SpringBootStandardConfiguration(WebEndpointProperties webEndpointProperties) {
        this.webEndpointProperties = webEndpointProperties;
        this.initWebEndpoint();
    }

    private void initWebEndpoint() {
        Set<String> include = webEndpointProperties.getExposure().getInclude();
        String[] defaults = EndpointExposure.WEB.getDefaultIncludes();
        for (String def : defaults) {
            include.add(def);
        }
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorResponseAdvisor.class)
    public ErrorResponseAdvisor errorResponseAdvisor() {
        LOG.info("[Bytehonor] ErrorResponseAdvisor");
        return new ErrorResponseAdvisor();
    }

    @Bean
    @ConditionalOnMissingBean(value = JsonResponseAdvisor.class)
    public JsonResponseAdvisor jsonResponseAdvisor() {
        LOG.info("[Bytehonor] JsonResponseAdvisor");
        return new JsonResponseAdvisor();
    }

    @Bean
    @ConditionalOnMissingBean(value = ServerWebMvcConfigurer.class)
    public ServerWebMvcConfigurer serverWebMvcConfigurer() {
        LOG.info("[Bytehonor] ServerWebMvcConfigurer");
        return new ServerWebMvcConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean(value = SchedulerControllerEndpoint.class)
    public SchedulerControllerEndpoint schedulerControllerEndpoint() {
        Set<String> include = webEndpointProperties.getExposure().getInclude();
        include.add(SpringServerConstants.SCHEDULER_ENDPOINT);
        LOG.info("[Bytehonor] SchedulerControllerEndpoint, include:{}", include);
        return new SchedulerControllerEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = ApplicationReadyEventListener.class)
    public ApplicationReadyEventListener applicationReadyEventListener() {
        LOG.info("[Bytehonor] ApplicationReadyEventListener");
        return new ApplicationReadyEventListener();
    }
}
