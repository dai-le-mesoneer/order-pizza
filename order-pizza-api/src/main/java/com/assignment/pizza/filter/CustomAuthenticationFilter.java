package com.assignment.pizza.filter;

import com.assignment.pizza.common.Constants;
import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.domain.repository.UserRepository;
import com.assignment.pizza.payload.response.LoginSuccessResponse;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author dai.le
 * @since 23/10/2024
 */

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final long EXPIRE_DURATION_ACCESS_TOKEN = 2L * 60 * 60 * 1000; // 2h

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        ResponseDTO<Object> responseObj = ResponseDTO.<Object>newBuilder()
                .setSuccess(false)
                .setMessage(failed.getMessage())
                .setCode(ErrorCode.AUTHENTICATION_FAILURE)
                .build();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        new ObjectMapper().writeValue(response.getOutputStream(), responseObj);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
            String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authToken);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        var account = userRepository.findByUsername(user.getUsername()).orElseThrow(() ->
                new IllegalStateException("User not found with username " + user.getUsername()));
        Algorithm algorithm = Algorithm.HMAC256(Constants.BYTE_CODE.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_DURATION_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().findFirst().get().getAuthority())
                .withClaim("account_id", account.getId())
                .sign(algorithm);
        var res = ResponseDTO.newBuilder().setSuccess(true)
                .setData(LoginSuccessResponse.builder()
                        .setAccessToken(access_token)
                        .setRole(account.getRole().getRole())
                        .setUsername(account.getUsername())
                        .setUserId(account.getId())
                        .setRoleId(account.getRole().getId())
                        .build())
                .build();

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), res);
    }
}
