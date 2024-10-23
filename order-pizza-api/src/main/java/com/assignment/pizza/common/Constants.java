package com.assignment.pizza.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dai.le
 * @since 23/10/2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public final static String BYTE_CODE = "p122@";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String LOGIN_PATH = "/login";
    public static final List<String> UN_AUTHENTICATION_PATH = List.of(LOGIN_PATH);
}
