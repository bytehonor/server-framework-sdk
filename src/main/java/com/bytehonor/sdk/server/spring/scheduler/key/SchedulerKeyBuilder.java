package com.bytehonor.sdk.server.spring.scheduler.key;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.define.spring.constant.DateConstants;
import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;

public class SchedulerKeyBuilder {

    public static String make(String prefix, LocalDateTime ldt) {
        Objects.requireNonNull(prefix, "prefix");
        Objects.requireNonNull(ldt, "ldt");

        return new StringBuilder().append(prefix).append("/").append(ldt.format(DateConstants.YYYY_MM_DD_HH_MM))
                .toString();
    }

    public static String make(String prefix, long time) {
        Objects.requireNonNull(prefix, "prefix");

        return make(prefix, LocalDateTimeUtils.fromTimestamp(time));
    }
}
