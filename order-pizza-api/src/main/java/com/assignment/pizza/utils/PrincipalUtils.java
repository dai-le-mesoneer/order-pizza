package com.assignment.pizza.utils;

import com.assignment.pizza.domain.dto.PizzaStorePrincipal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author dai.le
 * @since 24/10/2024
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrincipalUtils {
    public static PizzaStorePrincipal getPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return PizzaStorePrincipal.builder()
                    .username((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .role(SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getAuthorities()
                            .stream()
                            .findFirst()
                            .orElseThrow()
                            .getAuthority())
                    .build();
        }
        return null;
    }
}
