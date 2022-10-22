package com.peaqock.aml.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.peaqock.aml.domain.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"password"})
public class UserPrincipal extends User {

    private final UUID userId;

    public UserPrincipal(UserEntity user) {
        super(user.getUsername(), user.getEncryptedPassword(), user.getActive(),
                true, true, true,
                user.getGrantedAuthorities());
        this.userId = user.getId();
    }
}
