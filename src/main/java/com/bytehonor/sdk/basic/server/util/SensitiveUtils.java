package com.bytehonor.sdk.basic.server.util;

import java.util.Objects;

public class SensitiveUtils {

    public static long LONG_MAX = Long.MAX_VALUE / 2;

    private static int B = 1240;

    public static String encodeLong(long val) {
        val = LONG_MAX - (val * B);
        return Long.toString(val, Character.MAX_RADIX);
    }

    public static long decodeLong(String val) {
        Objects.requireNonNull(val, "val");
        long lon = Long.parseLong(val, Character.MAX_RADIX);
        return (LONG_MAX - lon) / B;
    }

//    public static void main(String[] args) {
//        long ln = 100;
//        System.out.println(ln);
//        for (int i = 0; i < 500; i++) {
//            String sl = encodeLong(ln);
//            System.out.println(ln + ", " + sl + ", " + (ln == decodeLong(sl)));
//            ln += System.currentTimeMillis();
//        }
//        
//        try {
//            long d = decodeLong("za=");
//            System.out.println(d);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
