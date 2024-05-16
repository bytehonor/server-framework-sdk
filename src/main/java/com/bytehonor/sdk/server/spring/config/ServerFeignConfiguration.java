package com.bytehonor.sdk.server.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bytehonor.sdk.server.spring.web.feign.FeignRequestInterceptor;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ FeignAutoConfiguration.class, EnableFeignClients.class })
@AutoConfigureAfter(FeignAutoConfiguration.class)
public class ServerFeignConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerFeignConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(value = FeignRequestInterceptor.class)
    FeignRequestInterceptor feignRequestInterceptor() {
        LOG.info("[Bytehonor] FeignRequestInterceptor");
        return new FeignRequestInterceptor();
    }
}
