package com.peaqock.aml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "email.props")
public class EmailProps {

//    @Valid
//    @NotNull
//    private EmailPropDto createUser;
}
