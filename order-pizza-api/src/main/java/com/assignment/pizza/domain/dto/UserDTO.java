package com.assignment.pizza.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class UserDTO {
    private String username;
    private String password;
    private String role;
}
