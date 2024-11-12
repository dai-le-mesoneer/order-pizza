package com.assignment.pizza.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

public class Constants {
    public final static String BYTE_CODE = "p122@1";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String LOGIN_PATH = "/login";
    public static final List<String> UN_AUTHENTICATION_PATH = List.of(LOGIN_PATH);
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ROLE {
        public static final String RECEPTIONIST = "RECEPTIONIST";
        public static final String CHEF = "CHEF";
        public static final String DELIVERY = "DELIVERY";
    }
}
