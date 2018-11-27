package com.bytehonor.standard.sping.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.bytehonor.standard.sping.server.config.SpringCloudStandardConfiguration;

/**
 * 启动注解，引入自定义Bean
 * 
 * @author lijianqiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SpringCloudStandardConfiguration.class)
public @interface SpringCloudStandard {

}
