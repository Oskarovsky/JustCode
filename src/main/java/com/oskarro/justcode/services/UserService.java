package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.domains.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
