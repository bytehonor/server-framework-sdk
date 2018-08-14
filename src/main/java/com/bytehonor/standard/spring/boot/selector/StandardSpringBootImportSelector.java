package com.bytehonor.standard.spring.boot.selector;

import com.bytehonor.standard.spring.boot.config.StandardSpringBootConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class StandardSpringBootImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{StandardSpringBootConfiguration.class.getName()};
    }
}
