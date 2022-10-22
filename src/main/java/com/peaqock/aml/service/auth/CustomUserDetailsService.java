package com.peaqock.aml.service.auth;

import com.peaqock.aml.dao.UserRepository;
import com.peaqock.aml.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find this user: %s", username)));
    }

    @Transactional
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(userId))
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find this user: %s", userId)));
    }
}
