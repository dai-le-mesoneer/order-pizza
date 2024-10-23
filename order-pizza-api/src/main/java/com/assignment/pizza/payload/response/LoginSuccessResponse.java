package com.assignment.pizza.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dai.le
 * @since 23/10/2024
 */

@Builder(setterPrefix = "set")
@Getter
@Setter
public class LoginSuccessResponse {
    private String accessToken;
    private String role;
    private String username;
    private Long userId;
    private Long roleId;
}
