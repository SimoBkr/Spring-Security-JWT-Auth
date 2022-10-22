package com.peaqock.aml.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String imageUrl;
    private String sourceUrl;
    private String sourceDomain;


}

