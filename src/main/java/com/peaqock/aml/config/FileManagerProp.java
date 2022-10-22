package com.peaqock.aml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
@Configuration
@ConfigurationProperties("file-manager")
public class FileManagerProp {

    /**
     * The location of the file store.
     */
    @NotNull
    private Resource fileStore;
}
