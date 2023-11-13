package com.example.saver.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.saver.model.Role;
import com.example.saver.model.User;
import com.example.saver.repository.RoleRepository;
import com.example.saver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> {
            logger.warn("User:'{}' not found", username);
            return new UsernameNotFoundException(String.format("User %s no find", username));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
    }

    public void createNewUser(User user) {
        //TODO Add check user; we have/haven't this user
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }
}
