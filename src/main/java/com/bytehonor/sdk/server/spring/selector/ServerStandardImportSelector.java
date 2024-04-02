package com.bytehonor.sdk.server.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.config.ServerCommonConfiguration;
import com.bytehonor.sdk.server.spring.config.ServerStandardConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { ServerCommonConfiguration.class.getName(), ServerStandardConfiguration.class.getName() };
    }
}
