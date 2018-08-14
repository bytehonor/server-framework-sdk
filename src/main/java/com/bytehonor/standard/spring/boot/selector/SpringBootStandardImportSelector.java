package com.bytehonor.standard.spring.boot.selector;

import com.bytehonor.standard.spring.boot.config.SpringBootStandardConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SpringBootStandardImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SpringBootStandardConfiguration.class.getName()};
    }
}
