package com.peaqock.aml.dto;

import com.peaqock.aml.shared.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Getter
@Setter
@Lazy
@Configuration
@ConfigurationProperties("data.init")
public class DataInit {

    private List<UserInit> users;

    @Getter
    @Setter
    public static class UserInit {

        private String firstName;

        private String lastName;

        private String username;

        private String password;

        private RoleEnum role;
    }
}
