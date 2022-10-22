package com.peaqock.aml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailPropDto {

    @NotNull
    private String subject;

    private String btnText;

    private String btnLink;

    @NotNull
    private String bodyText;
}
