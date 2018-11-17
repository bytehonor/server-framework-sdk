package com.bytehonor.standard.server.sping.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.standard.server.sping.config.SpringBootStandardConfiguration;

public class SpringBootStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SpringBootStandardConfiguration.class.getName()};
    }
}
