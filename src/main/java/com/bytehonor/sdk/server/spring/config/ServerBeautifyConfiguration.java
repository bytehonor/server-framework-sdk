package com.bytehonor.sdk.server.spring.config;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.server.spring.web.advisor.ResponseBeautifyAdvisor;
import com.bytehonor.sdk.server.spring.web.advisor.ResponseExceptionAdvisor;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(ServerCommonConfiguration.class)
public class ServerBeautifyConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerBeautifyConfiguration.class);

    public ServerBeautifyConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(value = ResponseExceptionAdvisor.class)
    public ResponseExceptionAdvisor responseExceptionAdvisor() {
        LOG.info("[Bytehonor] responseExceptionAdvisor");
        return new ResponseExceptionAdvisor();
    }

    @Bean
    @ConditionalOnMissingBean(value = ResponseBeautifyAdvisor.class)
    public ResponseBeautifyAdvisor responseBeautifyAdvisor() {
        LOG.info("[Bytehonor] responseBeautifyAdvisor");
        return new ResponseBeautifyAdvisor();
    }
}
