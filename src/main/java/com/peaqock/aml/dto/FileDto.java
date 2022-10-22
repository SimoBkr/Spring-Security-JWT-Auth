package com.peaqock.aml.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String fileKey;
    private String message;

}
