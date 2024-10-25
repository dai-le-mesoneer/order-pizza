package com.assignment.pizza.domain.dto;

import com.assignment.pizza.common.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dai.le
 * @since 24/10/2024
 */

@Builder
@Getter
@Setter
public class PizzaStorePrincipal {
    private String username;
    private String role;

    public boolean isReceptionist() {
        return Role.RECEPTIONIST.getName().equals(this.role);
    }

    public boolean isChef() {
        return Role.CHEF.getName().equals(this.role);
    }

    public boolean isDelivery() {
        return Role.DELIVERY.getName().equals(this.role);
    }
}
