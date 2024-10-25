package com.assignment.pizza.security;

import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author dai.le
 * @since 23/10/2024
 */
@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Unauthorized: {}", request.getRequestURI());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        var error = ResponseDTO.newBuilder()
                .setSuccess(false)
                .setMessage(authException.getMessage())
                .setCode(ErrorCode.UNAUTHORIZED)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
