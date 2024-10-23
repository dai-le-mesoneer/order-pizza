package com.assignment.pizza.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatetimeUtils {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm";

    public static String timestampToStringWithDefaultZone(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        return format.format(timestamp);
    }
}
