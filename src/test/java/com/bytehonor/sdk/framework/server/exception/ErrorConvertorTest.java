package com.bytehonor.sdk.framework.server.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorConvertorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorConvertorTest.class);
    
    @Test
    public void testFormat() {
        try {
            throw new SpringServerException("测试");
        } catch(Exception e) {
            LOG.info("format:{}", ErrorConvertor.format(e));
        }
    }

}
