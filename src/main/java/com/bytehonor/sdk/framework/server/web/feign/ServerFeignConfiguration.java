package com.bytehonor.sdk.framework.server.web.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bytehonor.sdk.framework.server.web.selector.SpringServerAutoConfiguration;

/**
 * Feign 相关自动装配，默认注入请求拦截器。
 * 
 * @author lijianqiang
 */
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(SpringServerAutoConfiguration.class)
public class ServerFeignConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerFeignConfiguration.class);

    /**
     * 注册 Feign 请求拦截器。
     * 
     * @return Feign 请求拦截器
     */
    @Bean
    @ConditionalOnMissingBean(value = FeignRequestInterceptor.class)
    FeignRequestInterceptor feignRequestInterceptor() {
        LOG.info("[Bytehonor] FeignRequestInterceptor");
        return new FeignRequestInterceptor();
    }
}
