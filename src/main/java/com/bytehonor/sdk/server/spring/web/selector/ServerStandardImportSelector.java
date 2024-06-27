package com.bytehonor.sdk.server.spring.web.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.sdk.server.spring.web.SpringServerAutoConfiguration;
import com.bytehonor.sdk.server.spring.web.response.ResponseStandardConfiguration;

/**
 * @author lijianqiang
 *
 */
public class ServerStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { SpringServerAutoConfiguration.class.getName(),
                ResponseStandardConfiguration.class.getName(), };
    }
}
