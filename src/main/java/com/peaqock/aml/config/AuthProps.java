package com.peaqock.aml.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties("auth")
public class AuthProps {

    @Valid
    @NotNull
    private Jwt jwt;

    @Getter
    @Setter
    public static class Jwt {

        /**
         * The location of the key store.
         */
        @NotNull
        private Resource keyStore;

        /**
         * The alias of the key from the key store
         */
        @NotEmpty
        private String keyAlias;

        /**
         * The password of the key from the key store
         */
        @NotEmpty
        private String keyPassword;

        @NotEmpty
        private String issuer = "issuer";

        @NotNull
        private Duration accessTokenValidity = Duration.ofHours(1);
    }
}
