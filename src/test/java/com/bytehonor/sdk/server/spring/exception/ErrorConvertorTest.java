package com.bytehonor.sdk.server.spring.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.spring.exception.StandardException;

public class ErrorConvertorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorConvertorTest.class);
    
    @Test
    public void testFormat() {
        try {
            throw new SpringServerException("测试");
        } catch(Exception e) {
            LOG.info("format:{}", ErrorConvertor.format(e));
        }
        
        try {
            throw new StandardException(1, "啊手动阀手动阀");
        } catch(Exception e) {
            LOG.info("format:{}", ErrorConvertor.format(e));
        }
    }

}
