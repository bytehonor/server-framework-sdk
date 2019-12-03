package com.bytehonor.sdk.basic.server.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class LocalDateTimeUtils {

    public final static DateTimeFormatter FORMATTER_DEFAULT;

    public final static DateTimeFormatter FORMATTER_ZONE;

    static {
        FORMATTER_ZONE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z", Locale.CHINA);
        FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    }

    public static long toTimestamp(LocalDateTime ldt) {
        Objects.requireNonNull(ldt, "ldt");
        return ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long toTimestamp(String src) {
        return toTimestamp(src, FORMATTER_DEFAULT);
    }

    public static long toTimestamp(String src, DateTimeFormatter formatter) {
        Objects.requireNonNull(src, "src");
        Objects.requireNonNull(formatter, "formatter");
        LocalDateTime ldt = LocalDateTime.parse(src, formatter);
        return toTimestamp(ldt);
    }

    public static String format(LocalDateTime ldt) {
        return format(ldt, FORMATTER_DEFAULT);
    }

    public static String format(LocalDateTime ldt, DateTimeFormatter formatter) {
        Objects.requireNonNull(ldt, "ldt");
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(ldt);
    }

    public static String format(long time) {
        return format(time, FORMATTER_DEFAULT);
    }

    public static String format(long time, DateTimeFormatter formatter) {
        return format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()), formatter);
    }

}
