package com.bytehonor.sdk.framework.server.web.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lijianqiang
 *
 */
public class ServerStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { SpringServerAutoConfiguration.class.getName(),
                SpringStandardAutoConfiguration.class.getName(), };
    }
}
