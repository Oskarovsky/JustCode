package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.domains.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User save(UserRegistrationDto registration);
}
