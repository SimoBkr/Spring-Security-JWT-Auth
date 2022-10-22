package com.peaqock.aml.config;

import com.peaqock.aml.dao.UserRepository;
import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.dto.DataInit;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Lazy
@Configuration
@Transactional
@RequiredArgsConstructor
public class DataLoader {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final DataInit dataInit;

    @Bean
    public ApplicationRunner init() {
        return args -> dataInit.getUsers()
                .forEach(this::createUser);
    }

    private void createUser(DataInit.UserInit init) {
        if (!userRepository.existsByUsernameIgnoreCase(init.getUsername())) {
            var user = UserEntity.builder()
                    .email(init.getUsername())
                    .lastName(init.getLastName())
                    .firstName(init.getFirstName())
                    .username(init.getUsername().toLowerCase().trim())
                    .encryptedPassword(encoder.encode(init.getPassword()))
                    .roles(Set.of(init.getRole()))
                    .active(true)
                    .build();
            userRepository.save(user);
        }
    }
}
