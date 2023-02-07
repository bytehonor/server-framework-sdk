package com.bytehonor.sdk.server.spring.getter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestKeyOptTest {

    private static final Logger LOG = LoggerFactory.getLogger(RequestKeyOptTest.class);

    @Test
    public void test() {
        String key = "create_at";
        String opt = "gt";
        String raw1 = key + RequestKeyOpt.SPL + opt;
        RequestKeyOpt modle1 = RequestKeyOpt.parse(raw1);

        LOG.info("1 key:{}, opt:{}", modle1.getKey(), modle1.getOpt());
        boolean isOk1 = key.equals(modle1.getKey()) && opt.equals(modle1.getOpt());

        RequestKeyOpt modle2 = RequestKeyOpt.parse(key);

        LOG.info("2 key:{}, opt:{}", modle2.getKey(), modle2.getOpt());
        boolean isOk2 = key.equals(modle2.getKey()) && RequestKeyOpt.EQ.equals(modle2.getOpt());

        assertTrue("*test*", isOk1 && isOk2);
    }

}
