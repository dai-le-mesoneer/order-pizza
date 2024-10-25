package com.assignment.pizza.security;

import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @author dai.le
 * @since 23/10/2024
 */

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // handler exception
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        var responseObj = ResponseDTO.newBuilder()
                .setSuccess(false)
                .setMessage("Bad request")
                .setCode(ErrorCode.ACCESS_DENIED)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), responseObj);
    }
}
