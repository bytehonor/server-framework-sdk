package com.bytehonor.sdk.framework.server.web.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.framework.server.web.advisor.GlobalExceptionAdvisor;
import com.bytehonor.sdk.framework.server.web.advisor.ResponseStandardAdvisor;

import jakarta.servlet.Servlet;

/**
 * 标准响应风格自动装配，统一异常与返回体处理。
 * 
 * @author lijianqiang
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(SpringServerAutoConfiguration.class)
public class SpringStandardAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringStandardAutoConfiguration.class);

    /**
     * 注册全局异常处理器。
     * 
     * @return 全局异常处理器
     */
    @Bean
    @ConditionalOnMissingBean(value = GlobalExceptionAdvisor.class)
    GlobalExceptionAdvisor globalExceptionAdvisor() {
        LOG.info("[Bytehonor] GlobalExceptionAdvisor");
        return new GlobalExceptionAdvisor();
    }

    /**
     * 注册统一返回包装处理器。
     * 
     * @return 统一返回处理器
     */
    @Bean
    @ConditionalOnMissingBean(value = ResponseStandardAdvisor.class)
    ResponseStandardAdvisor responseStandardAdvisor() {
        LOG.info("[Bytehonor] ResponseStandardAdvisor");
        return new ResponseStandardAdvisor();
    }
}
