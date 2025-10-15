package com.bytehonor.sdk.framework.server.web.request;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyOptPairTest {

    private static final Logger LOG = LoggerFactory.getLogger(KeyOptPairTest.class);

    @Test
    public void test() {
        String key = "create_at";
        String opt = "gt";
        String raw1 = key + KeyOptPair.SPL + opt;
        KeyOptPair modle1 = KeyOptPair.parse(raw1);

        LOG.info("1 key:{}, opt:{}", modle1.getKey(), modle1.getOpt());
        boolean isOk1 = key.equals(modle1.getKey()) && opt.equals(modle1.getOpt());

        KeyOptPair modle2 = KeyOptPair.parse(key);

        LOG.info("2 key:{}, opt:{}", modle2.getKey(), modle2.getOpt());
        boolean isOk2 = key.equals(modle2.getKey()) && KeyOptPair.EQ.equals(modle2.getOpt());

        assertTrue("*test*", isOk1 && isOk2);
    }

}
