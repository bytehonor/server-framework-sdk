package com.bytehonor.sdk.server.bytehonor.scheduler.key;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.bytehonor.sdk.lang.bytehonor.util.LocalDateTimeUtils;

public class JobKeyMaker {

    public final static DateTimeFormatter FORMATTER_JOB;

    static {
        FORMATTER_JOB = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm", Locale.CHINA);
    }

    public static String make(String prefix, LocalDateTime ldt) {
        return new StringBuilder().append(prefix).append("/").append(LocalDateTimeUtils.format(ldt, FORMATTER_JOB))
                .toString();
    }

//    public static LocalDateTime from(String val) {
//        long time = LocalDateTimeUtils.toTimestamp(val, FORMATTER_JOB);
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
//    }
//
//    public static void main(String[] args) {
//        // xxxx/2021-04-08-21-20
//        System.out.println(make("xxxx", LocalDateTime.now()));
//    }
}
