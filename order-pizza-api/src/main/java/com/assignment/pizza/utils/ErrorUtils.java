package com.assignment.pizza.utils;

import com.assignment.pizza.payload.response.ErrorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author dai.le
 * @since 21/10/2024
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorUtils {
    public static List<ErrorDTO> createListErrors(ErrorDTO ...errorDTOS) {
        return Arrays.stream(errorDTOS).filter(Objects::nonNull).toList();
    }

    public static <T extends Enum<T>> ErrorDTO createErrorDTO(String key, T code) {
        return ErrorDTO.of(key, code);
    }

    public static <T extends Enum<T>> ErrorDTO createErrorDTO(String key, T code, String message) {
        return ErrorDTO.of(key, code, message);
    }
}
