package com.peaqock.aml.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aml_senario")
public class AmlScenarioEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "matricule")
    private String subjectId;

    @Column(name = "nom_senario")
    private String scenarioName;

    @Column(name = "code_senario")
    private String scenarioCode;

    @Column(name = "description_senario")
    private String scenarioDescription;

    @Column(name = "raison_sociale")
    private String raisonSocial;

    @Column(name = "policy_key")
    private String policyKey;

    @Column(name = "alert_key")
    private String alertKey;

    @Column(name = "complexite")
    private String complexity;

    @Column(name = "has_comment")
    @Builder.Default
    private Boolean hasComment = false;

    @Column(name = "comment")
    private String comment;

    @Column(name = "score")
    private Long score;
}
