package com.peaqock.aml.dto;

import com.peaqock.aml.domain.Commentaire;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RapportDto {

    private String searchedName;

    private String flag;

    private String status;

    private Commentaire comment;

    private long pepId;
}
