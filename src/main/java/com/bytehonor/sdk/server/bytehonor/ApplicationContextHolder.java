package com.bytehonor.sdk.server.bytehonor;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lijianqiang
 *
 */
public class ApplicationContextHolder {

	private ConfigurableApplicationContext applicationContext;

	private ApplicationContextHolder() {

	}

	private static class LazyHolder {
		private static ApplicationContextHolder INStANCE = new ApplicationContextHolder();
	}

	private static ApplicationContextHolder getInstance() {
		return LazyHolder.INStANCE;
	}

	public ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static ConfigurableApplicationContext getContext() {
		return getInstance().getApplicationContext();
	}

	public static void setContext(ConfigurableApplicationContext applicationContext) {
		getInstance().setApplicationContext(applicationContext);
	}
	
	public static <T> T getBean(Class<T> requiredType) {
		return getInstance().getApplicationContext().getBean(requiredType);
	}

}
