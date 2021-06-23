package com.bytehonor.sdk.server.bytehonor.config;

import javax.servlet.Servlet;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(SpringCloudStandardProperties.class)
public class SpringCloudStandardConfiguration {

//	private static final Logger LOG = LoggerFactory.getLogger(SpringCloudStandardConfiguration.class);

//	@Bean
//	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-strategy-enable", matchIfMissing = true)
//	@ConditionalOnClass({ HystrixConcurrencyStrategy.class })
//	public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
//		LOG.info("[standard cloud bean] FeignHystrixConcurrencyStrategy");
//		return new FeignHystrixConcurrencyStrategy();
//	}

//	@Bean
//	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-interceptor-enable", matchIfMissing = true)
//	@ConditionalOnMissingBean
//	@ConditionalOnClass({ RequestInterceptor.class })
//	public FeignCoreInterceptor feignCoreInterceptor() {
//		LOG.info("[standard cloud bean] FeignCoreInterceptor");
//		return new FeignCoreInterceptor();
//	}

}
