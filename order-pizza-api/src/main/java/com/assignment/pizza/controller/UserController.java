package com.assignment.pizza.controller;

import com.assignment.pizza.domain.dto.UserDTO;
import com.assignment.pizza.payload.request.auth.LoginRequest;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.assignment.pizza.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author dai.le
 * @since 22/10/2024
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login API", description = "Login API")
    public ResponseDTO<UserDTO> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
