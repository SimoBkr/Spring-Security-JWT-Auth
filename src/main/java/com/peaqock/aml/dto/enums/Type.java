package com.peaqock.aml.dto.enums;

public enum Type {
    INDIVIDUAL("INDIVIDUAL"),
    ENTITY("ENTITY"),
    UNKNOWN("UNKNOWN");

    public final String label;

    Type(String label) {
        this.label = label;
    }
}
