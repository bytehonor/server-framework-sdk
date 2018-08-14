package com.bytehonor.standard.spring.boot.annotation;

import com.bytehonor.standard.spring.boot.selector.StandardSpringBootImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动注解，引入自定义Bean
 * 
 * @author lijianqiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(StandardSpringBootImportSelector.class)
public @interface StandardSpringBoot {
}
