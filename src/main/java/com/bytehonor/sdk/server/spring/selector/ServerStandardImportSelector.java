package com.bytehonor.sdk.server.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.config.ServerWebConfiguration;
import com.bytehonor.sdk.server.spring.config.ResponseStandardConfiguration;
import com.bytehonor.sdk.server.spring.config.ServerFeignConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { ServerWebConfiguration.class.getName(), ServerFeignConfiguration.class.getName(),
                ResponseStandardConfiguration.class.getName() };
    }
}
