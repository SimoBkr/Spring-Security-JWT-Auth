package com.peaqock.aml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmlScenarioDTO {

    private Long id;

    private String subjectId;

    private String scenarioName;

    private String scenarioCode;

    private String scenarioDescription;

    private String raisonSocial;

    private String complexity;

    private String policyKey;

    private String alertKey;

    private Boolean hasComment;

    private String comment;

    private Long score;
}
