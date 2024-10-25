package com.assignment.pizza.filter;

import com.assignment.pizza.common.Constants;
import com.assignment.pizza.common.ErrorCode;
import com.assignment.pizza.payload.response.ResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author dai.le
 * @since 23/10/2024
 */

@Log4j2
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private static final Pattern URI_PUBLIC_PATTERN = Pattern.compile("^/public/.*");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Constants.UN_AUTHENTICATION_PATH.contains(request.getServletPath()) ||
                URI_PUBLIC_PATTERN.matcher(request.getServletPath()).matches()) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(Constants.PREFIX_TOKEN)) {
                try {
                    String token = authorizationHeader.substring(Constants.PREFIX_TOKEN.length());
                    Algorithm algorithm = Algorithm.HMAC256(Constants.BYTE_CODE.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String role = decodedJWT.getClaim("roles").asString();
                    Collection<GrantedAuthority> authorities = new ArrayList<>();

                    authorities.add(new SimpleGrantedAuthority(role));

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } catch (TokenExpiredException e){
                    // Handler expired token exception
                    log.warn("Token expired");
                    var responseObj = ResponseDTO.newBuilder()
                            .setSuccess(false)
                            .setMessage("Token expired")
                            .setCode(ErrorCode.TOKEN_EXPIRED)
                            .build();
                    response.setStatus(FORBIDDEN.value());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), responseObj);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
