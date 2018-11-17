package com.bytehonor.standard.server.sping.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "standard.springcloud")
public class SpringCloudStandardProperties {

	/**
	 * Feign Interceptor Enable, default true
	 */
	private boolean feignInterceptorEnable = true;

	/**
	 * Feign Hystrix Strategy Enable, default true
	 */
	private boolean feignStrategyEnable = true;
	
	/**
	 * Feign Rpc sign Enable, default true
	 */
	private boolean feignRpcSignEnable = true;

	public boolean isFeignInterceptorEnable() {
		return feignInterceptorEnable;
	}

	public void setFeignInterceptorEnable(boolean feignInterceptorEnable) {
		this.feignInterceptorEnable = feignInterceptorEnable;
	}

	public boolean isFeignStrategyEnable() {
		return feignStrategyEnable;
	}

	public void setFeignStrategyEnable(boolean feignStrategyEnable) {
		this.feignStrategyEnable = feignStrategyEnable;
	}

	public boolean isFeignRpcSignEnable() {
		return feignRpcSignEnable;
	}

	public void setFeignRpcSignEnable(boolean feignRpcSignEnable) {
		this.feignRpcSignEnable = feignRpcSignEnable;
	}

}
