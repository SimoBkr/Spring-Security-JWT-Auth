package com.peaqock.aml.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    public static String generateAuthorizationId(String id) {
        return id + DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(LocalDateTime.now());
    }
}
