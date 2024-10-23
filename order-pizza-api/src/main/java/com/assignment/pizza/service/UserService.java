package com.assignment.pizza.service;

import com.assignment.pizza.domain.dto.UserDTO;
import com.assignment.pizza.payload.request.auth.LoginRequest;
import com.assignment.pizza.payload.response.ResponseDTO;

/**
 * @author dai.le
 * @since 22/10/2024
 */
public interface UserService {
    ResponseDTO<UserDTO> login(LoginRequest request);
}
