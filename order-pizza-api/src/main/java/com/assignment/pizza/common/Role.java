package com.assignment.pizza.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Getter
@AllArgsConstructor
public enum Role {
    RECEPTIONIST("RECEPTIONIST"),
    CHEF("CHEF"),
    DELIVERY("DELIVERY");
    private final String name;
}
