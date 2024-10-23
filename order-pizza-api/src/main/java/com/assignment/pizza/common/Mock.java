package com.assignment.pizza.common;

import com.assignment.pizza.domain.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mock {
    public static final List<UserDTO> MOCK_USERS = List.of(
            UserDTO.newBuilder()
                    .setUsername("receptionist")
                    .setPassword("123456")
                    .setRole(Role.RECEPTIONIST.name())
                    .build(),
            UserDTO.newBuilder()
                    .setUsername("chef")
                    .setPassword("123456")
                    .setRole(Role.CHEF.name())
                    .build(),
            UserDTO.newBuilder()
                    .setUsername("delivery")
                    .setPassword("123456")
                    .setRole(Role.DELIVERY.name()).build()
    );
}
