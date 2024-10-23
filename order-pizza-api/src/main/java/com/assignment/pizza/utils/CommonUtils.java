package com.assignment.pizza.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
    public static final Pattern PHONE_REGEX = Pattern.compile("^[- +()0-9]+$");

    public static boolean isInvalidPhone(String phone) {
        return !PHONE_REGEX.matcher(phone).matches();
    }
}
