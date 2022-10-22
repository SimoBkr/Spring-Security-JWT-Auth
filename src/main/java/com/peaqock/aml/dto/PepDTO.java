package com.peaqock.aml.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PepDTO {

    private String name;
    private String tlName;
    private String lastName;
    private String lastNames;
    private String givenNames;
    private String aliasNames;
    private String aliasGivenNames;
    private String gender;
    private String citizenship;
    private String nationality;
    private String address;
    private String pepType;
    private String entityType;
    private String sourceType;
    private String dateOfBirth;
    private String placeOfBirth;
    private String siblings;
    private String spouse;
    private String description;
    private String occupations;
    private String titles;
    private String politicalParties;
    private String sanctionDetails;
    private String citizenshipRemarks;
    private String otherInformation;
    private String links;
}
