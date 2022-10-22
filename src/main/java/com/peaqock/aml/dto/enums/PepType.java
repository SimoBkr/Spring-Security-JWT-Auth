package com.peaqock.aml.dto.enums;

public enum PepType {
    PEP("PEP"),
    SANCTIONS("SANCTIONS"),
    WATCHLIST("WATCHLIST");

    public final String label;

    PepType(String label) {
        this.label = label;
    }
}
