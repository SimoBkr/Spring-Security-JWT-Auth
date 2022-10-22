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
@Table(name = "aml_summary")
public class AmlSummaryEntity {

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

    @Column(name = "complexite")
    private String complexity;

    @Column(name = "nombre_alert")
    private Long alertNumber;

    @Column(name = "score_totale")
    private Long scoreTotal;

    @Column(name = "score_moyen")
    private Integer scoreAvg;
}
