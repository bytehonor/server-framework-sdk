package com.bytehonor.sdk.server.spring.config;

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

import jakarta.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(ServerWebConfiguration.class)
public class ResponseBeautifyConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseBeautifyConfiguration.class);

//    @Bean
//    @ConditionalOnMissingBean(value = ResponseExceptionAdvisor.class)
//    ResponseExceptionAdvisor responseExceptionAdvisor() {
//        LOG.info("[Bytehonor] ResponseExceptionAdvisor");
//        return new ResponseExceptionAdvisor();
//    }

    @Bean
    @ConditionalOnMissingBean(value = ResponseBeautifyAdvisor.class)
    ResponseBeautifyAdvisor responseBeautifyAdvisor() {
        LOG.info("[Bytehonor] ResponseBeautifyAdvisor");
        return new ResponseBeautifyAdvisor();
    }
}
