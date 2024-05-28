package com.bytehonor.sdk.server.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.config.ResponseBeautifyConfiguration;
import com.bytehonor.sdk.server.spring.config.ServerWebConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerBeautifyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { ServerWebConfiguration.class.getName(), ResponseBeautifyConfiguration.class.getName() };
    }
}
