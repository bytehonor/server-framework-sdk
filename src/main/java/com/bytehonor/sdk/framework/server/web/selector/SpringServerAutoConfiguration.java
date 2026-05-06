package com.bytehonor.sdk.framework.server.web.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.framework.server.listener.ApplicationReadyListener;
import com.bytehonor.sdk.framework.server.web.controller.ExampleController;
import com.bytehonor.sdk.framework.server.web.controller.SchedulerController;
import com.bytehonor.sdk.framework.server.web.mvc.ServerWebMvcConfigurer;

import jakarta.servlet.Servlet;

/**
 * 服务端基础自动装配，提供最小可用的 MVC 与示例端点组件。
 * 
 * @author lijianqiang
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(WebEndpointAutoConfiguration.class)
public class SpringServerAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringServerAutoConfiguration.class);

    /**
     * 注册基础 WebMvc 配置。
     * 
     * @return WebMvc 配置实例
     */
    @Bean
    @ConditionalOnMissingBean(value = ServerWebMvcConfigurer.class)
    ServerWebMvcConfigurer serverWebMvcConfigurer() {
        LOG.info("[Bytehonor] ServerWebMvcConfigurer");
        return new ServerWebMvcConfigurer();
    }

    /**
     * 注册应用启动完成事件监听器。
     * 
     * @return 启动监听器
     */
    @Bean
    @ConditionalOnMissingBean(value = ApplicationReadyListener.class)
    ApplicationReadyListener applicationReadyEventListener() {
        LOG.info("[Bytehonor] ApplicationReadyListener");
        return new ApplicationReadyListener();
    }

    /**
     * 注册示例控制器，用于快速验证框架接入。
     * 
     * @return 示例控制器
     */
    @Bean
    @ConditionalOnMissingBean(ExampleController.class)
    ExampleController exampleController() {
        LOG.info("[Bytehonor] ExampleController");
        return new ExampleController();
    }

    /**
     * 注册调度管理控制器。
     * 
     * @return 调度控制器
     */
    @Bean
    @ConditionalOnMissingBean(SchedulerController.class)
    SchedulerController schedulerController() {
        LOG.info("[Bytehonor] SchedulerController");
        return new SchedulerController();
    }
}
