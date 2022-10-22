package com.peaqock.aml.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.peaqock.aml.config.RawJsonDeserializer;
import com.peaqock.aml.config.RawJsonSerializer;
import com.peaqock.aml.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scripts")
public class Script extends AbstractEntity {

    @NotNull
    private String nom;

    private String langue;

    private Boolean is_configured;

    private Boolean is_shared;


    @JsonDeserialize(using = RawJsonDeserializer.class)
    @JsonSerialize(using = RawJsonSerializer.class)
    @NotNull
    @Lob
    private String data;
}
