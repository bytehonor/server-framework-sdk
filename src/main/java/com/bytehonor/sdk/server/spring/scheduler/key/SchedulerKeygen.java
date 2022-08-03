package com.bytehonor.sdk.server.spring.scheduler.key;

import java.time.LocalDateTime;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.constant.DateConstants;
import com.bytehonor.sdk.lang.spring.util.TimeFormatUtils;

/**
 * @author lijianqiang
 *
 */
public class SchedulerKeygen {

    public static String make(String prefix, LocalDateTime ldt) {
        Objects.requireNonNull(prefix, "prefix");
        Objects.requireNonNull(ldt, "ldt");

        return new StringBuilder().append(prefix).append(":").append(time(ldt)).toString();
    }

    public static String make(String prefix, long time) {
        Objects.requireNonNull(prefix, "prefix");

        return make(prefix, TimeFormatUtils.fromTimestamp(time));
    }

    private static String time(LocalDateTime ldt) {
        return ldt.format(DateConstants.YYYY_MM_DD_HH_MM);
    }
}
