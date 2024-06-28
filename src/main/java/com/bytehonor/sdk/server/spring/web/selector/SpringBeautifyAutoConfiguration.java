package com.bytehonor.sdk.server.spring.web.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.server.spring.web.advisor.GlobalExceptionAdvisor;
import com.bytehonor.sdk.server.spring.web.advisor.ResponseBeautifyAdvisor;

import jakarta.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(SpringServerAutoConfiguration.class)
public class SpringBeautifyAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBeautifyAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(value = GlobalExceptionAdvisor.class)
    GlobalExceptionAdvisor globalExceptionAdvisor() {
        LOG.info("[Bytehonor] GlobalExceptionAdvisor");
        return new GlobalExceptionAdvisor();
    }

    @Bean
    @ConditionalOnMissingBean(value = ResponseBeautifyAdvisor.class)
    ResponseBeautifyAdvisor responseBeautifyAdvisor() {
        LOG.info("[Bytehonor] ResponseBeautifyAdvisor");
        return new ResponseBeautifyAdvisor();
    }
}
