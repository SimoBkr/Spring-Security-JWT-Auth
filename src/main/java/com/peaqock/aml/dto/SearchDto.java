package com.peaqock.aml.dto;

import com.peaqock.aml.dto.enums.PepType;
import com.peaqock.aml.dto.enums.Type;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchDto {


    @NotNull
    private String name;
    private Type type;
    private String gender;
    private String cin;
    private String country;
    private String city;
    private int age;

    private List<PepType> pepTypes = new ArrayList<>();

    private int accuracy;


}
