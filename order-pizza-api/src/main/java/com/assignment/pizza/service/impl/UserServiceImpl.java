package com.assignment.pizza.service.impl;

import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.common.Mock;
import com.assignment.pizza.domain.dto.UserDTO;
import com.assignment.pizza.payload.request.auth.LoginRequest;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author dai.le
 * @since 22/10/2024
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseDTO<UserDTO> login(LoginRequest request) {
        var user = Mock.MOCK_USERS.stream()
                .filter(u -> u.getUsername().equals(request.getUsername()))
                .findFirst();

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return ResponseDTO.<UserDTO>newBuilder()
                    .setSuccess(true)
                    .setData(user.get())
                    .build();
        }
        return ResponseDTO.<UserDTO>newBuilder()
                .setCode(ErrorCode.INVALID)
                .setSuccess(true)
                .build();
    }
}
