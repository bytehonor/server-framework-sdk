package com.bytehonor.standard.sping.server.config;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.standard.sping.server.cloud.bean.feign.FeignCoreInterceptor;
import com.bytehonor.standard.sping.server.cloud.bean.hystrix.FeignHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import feign.RequestInterceptor;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ResourceProperties.class, SpringCloudStandardProperties.class })
public class SpringCloudStandardConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringCloudStandardConfiguration.class);
	
	@Bean
	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-strategy-enable", matchIfMissing = true)
	@ConditionalOnClass({ HystrixConcurrencyStrategy.class })
	public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
		LOG.info("[standard core bean] FeignHystrixConcurrencyStrategy");
		return new FeignHystrixConcurrencyStrategy();
	}
	
	@Bean
	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-interceptor-enable", matchIfMissing = true)
	@ConditionalOnMissingBean
	@ConditionalOnClass({ RequestInterceptor.class })
	public FeignCoreInterceptor feignCoreInterceptor() {
		LOG.info("[standard core bean] FeignCoreInterceptor");
		return new FeignCoreInterceptor();
	}

}
