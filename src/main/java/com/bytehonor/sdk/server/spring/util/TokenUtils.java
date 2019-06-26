package com.bytehonor.sdk.server.spring.util;

import java.util.Objects;

import org.springframework.util.Base64Utils;

import com.bytehonor.sdk.protocol.common.util.MD5Utils;
import com.bytehonor.sdk.protocol.common.util.RandomUtils;
import com.bytehonor.sdk.server.spring.getter.LongGetter;

public final class TokenUtils {

    public static String generate(long expireAt, String fromTerminal) {
        Objects.requireNonNull(fromTerminal, "fromTerminal");
        // text = expireAt_createAt_rand
        // sign = md5(text+fromTerminal)
        // base64(text&sign)
        long createAt = System.currentTimeMillis();
        int rand = RandomUtils.getInt(10000, 99999);
        StringBuilder sb = new StringBuilder();
        sb.append(expireAt).append("_").append(createAt).append("_").append(rand);
        String text = sb.toString();
        String sign = MD5Utils.md5(new StringBuilder().append(text).append("+").append(fromTerminal).toString());
        return base64Encode(new StringBuilder().append(text).append("&").append(sign).toString());
    }

    public static boolean check(String value, String fromTerminal) {
        Objects.requireNonNull(value, "value");
        String dec = base64Decode(value);
        int at = dec.indexOf('&');
        if (at < 1) {
            return false;
        }
        String text = dec.substring(0, at);
        if (text.length() < 15) {
            return false;
        }
        String sign = dec.substring(at + 1, dec.length());
        String md5 = MD5Utils.md5(new StringBuilder().append(text).append("+").append(fromTerminal).toString());
        if (md5.equals(sign) == false) {
            return false;
        }
        long now = System.currentTimeMillis();
        long expireAt = LongGetter.require(text.substring(0, 13), now - 1);
        return expireAt > now;

    }

    public static String base64Encode(String src) {
        Objects.requireNonNull(src, "src");
        return Base64Utils.encodeToUrlSafeString(src.getBytes());
    }

    public static String base64Decode(String src) {
        Objects.requireNonNull(src, "src");
        return new String(Base64Utils.decodeFromUrlSafeString(src));
    }

}
