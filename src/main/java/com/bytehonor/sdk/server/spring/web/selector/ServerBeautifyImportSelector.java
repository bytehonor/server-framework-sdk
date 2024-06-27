package com.bytehonor.sdk.server.spring.web.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.web.SpringServerAutoConfiguration;
import com.bytehonor.sdk.server.spring.web.response.ResponseBeautifyConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerBeautifyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { SpringServerAutoConfiguration.class.getName(), ResponseBeautifyConfiguration.class.getName() };
    }
}
