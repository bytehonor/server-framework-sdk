package com.bytehonor.sdk.server.spring.web.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bytehonor.sdk.server.spring.web.SpringServerAutoConfiguration;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(SpringServerAutoConfiguration.class)
public class ServerFeignConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerFeignConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(value = FeignRequestInterceptor.class)
    FeignRequestInterceptor feignRequestInterceptor() {
        LOG.info("[Bytehonor] FeignRequestInterceptor");
        return new FeignRequestInterceptor();
    }
}
