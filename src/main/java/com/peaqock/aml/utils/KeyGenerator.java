package com.peaqock.aml.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class KeyGenerator {

    public static String generate(String originalFilename) {
        return DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(LocalDateTime.now()) + "_"
                + Objects.requireNonNullElse(originalFilename, "file")
                .replace(" ", "_").toLowerCase();
    }
}
