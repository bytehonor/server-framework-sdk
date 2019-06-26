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
        // MTU2MTU1MzIxNzYxNF8xNTYxNTUzMjE3NDE0XzExNTQ4JjJmMzkzNjgxZjMyMzgwZjgyNmE5MThmOTc3NzNkZWM0
        if (value.length() != 88) {
            return false;
        }
        String dec = base64Decode(value);
        // 1561553217614_1561553217414_11548&2f393681f32380f826a918f97773dec4
        if ('&' != dec.charAt(33)) {
            return false;
        }
        String text = dec.substring(0, 33);
        if (text.length() < 15) {
            return false;
        }
        String sign = dec.substring(34, dec.length());
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
