package com.assignment.pizza.service.impl;

import com.assignment.pizza.domain.repository.UserRepository;
import com.assignment.pizza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dai.le
 * @since 22/10/2024
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalStateException("User not found"));
        return new User(username, user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().getRole())));
    }
}
