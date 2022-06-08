package com.bytehonor.sdk.server.spring.config;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.server.spring.web.advisor.ErrorResponseAdvisor;
import com.bytehonor.sdk.server.spring.web.advisor.JsonResponseAdvisor;
import com.bytehonor.sdk.server.spring.web.mvc.ServerWebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(SpringBootStandardProperties.class)
public class SpringBootStandardConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootStandardConfiguration.class);

//    @Autowired(required = false)
//    private List<ErrorViewResolver> errorViewResolvers;

//    private final ServerProperties serverProperties;
//
//    public SpringBootStandardConfiguration(ServerProperties serverProperties) {
//        this.serverProperties = serverProperties;
//    }

    // 2.3.0+ no used
//    @Bean
//    @ConditionalOnProperty(prefix = "server.core.web", name = "error-controller-enable", matchIfMissing = true)
//    @ConditionalOnMissingBean(value = ErrorController.class)
//    public CustomErrorController basicErrorController(ErrorAttributes errorAttributes) {
//        LOG.info("[standard boot bean] CustomErrorController");
//        return new CustomErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
//    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "error.advisor.enable", matchIfMissing = true)
    @ConditionalOnMissingBean(value = ErrorResponseAdvisor.class)
    public ErrorResponseAdvisor errorResponseAdvisor() {
        LOG.info("[Bytehonor] ErrorResponseAdvisor");
        return new ErrorResponseAdvisor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "response.advisor.enable", matchIfMissing = true)
    @ConditionalOnMissingBean(value = JsonResponseAdvisor.class)
    public JsonResponseAdvisor jsonResponseAdvisor() {
        LOG.info("[Bytehonor] JsonResponseAdvisor");
        return new JsonResponseAdvisor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "mvc-custom-enable", matchIfMissing = true)
    public ServerWebMvcConfigurer serverWebMvcConfigurer() {
        LOG.info("[Bytehonor] ServerWebMvcConfigurer ");
        return new ServerWebMvcConfigurer();
    }
}
