package com.assignment.pizza.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING(1),
    CANCELLED(2),
    CONFIRMED(3),
    COMPLETED(4),
    DELIVERED(5);
    private final Integer value;

    public static boolean isValid(Integer status) {
        return Arrays.stream(values()).anyMatch(s -> s.value.equals(status));
    }
}
