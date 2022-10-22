package com.peaqock.aml.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peps")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Pep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long score;

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
    private String cin;
    private String country;
    private String city;

    @Column(columnDefinition = "varchar(255) default 'INITIAL'")
    private String pepStatus;

    @Column(columnDefinition = "varchar(255) default '0'")
    private String sourceId;

    @Column(columnDefinition = "varchar(255) default 'global_watch_list'")
    private String sourceName;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "searchedPeps")
    @Builder.Default
    private List<History> histories = new ArrayList<>();


}
