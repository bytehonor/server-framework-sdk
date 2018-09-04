package com.bytehonor.standard.boot.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.bytehonor.standard.boot.spring.config.SpringBootStandardConfiguration;

public class SpringBootStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SpringBootStandardConfiguration.class.getName()};
    }
}
