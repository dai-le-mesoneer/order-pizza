package com.assignment.pizza.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dai-le-mesoneer
 * @since 10/17/24
 */

@Getter
@AllArgsConstructor
public enum ProductType {
    PIZZA(1, "PIZZA"),
    DRINK(2, "DRINK");
    private final Integer id;
    private final String name;

    /**
     * Given a string representing the product type, parse it into the corresponding enum value.
     * If the string does not match any valid product type, returns {@link #PIZZA} as the default.
     *
     * @param type the string value representing the product type
     * @return the parsed enum value
     */
    public static ProductType parse(String type) {
        return Arrays.stream(values())
                .filter(a -> a.getName().equalsIgnoreCase(type))
                .findFirst()
                .orElse(PIZZA);
    }

    /**
     * Given an integer representing the product type, parse it into the corresponding enum value.
     * If the integer does not match any valid product type, returns {@link #PIZZA} as the default.
     *
     * @param id the integer value representing the product type
     * @return the parsed enum value
     */
    public static ProductType of(Integer id) {
        return Arrays.stream(values())
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(PIZZA);
    }
}
