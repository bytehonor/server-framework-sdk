package com.bytehonor.sdk.server.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.config.ServerBeautifyConfiguration;
import com.bytehonor.sdk.server.spring.config.ServerCommonConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerBeautifyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { ServerCommonConfiguration.class.getName(), ServerBeautifyConfiguration.class.getName() };
    }
}
