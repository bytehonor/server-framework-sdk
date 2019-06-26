package com.bytehonor.sdk.server.spring.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(TokenUtilsTest.class);

    @Test
    public void test() {
        String fromTerminal = "browser";
        long now = System.currentTimeMillis();
        for (int i = 200; i < 205; i++) {
            String token = TokenUtils.generate(now + i, fromTerminal);
            LOG.info("ok:{}, token:{}", TokenUtils.check(token, fromTerminal), token);

        }
        assertTrue("test", true);
    }

}
