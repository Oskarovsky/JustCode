package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Role;
import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.domains.UserRegistrationDto;
import com.oskarro.justcode.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                user.get().getPassword(),
                mapRolesToAuthorities(user.get().getRoles()));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setImage("http://if.fjfi.cvut.cz/joomla3//media/com_if/images/profile-picture.png");
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
