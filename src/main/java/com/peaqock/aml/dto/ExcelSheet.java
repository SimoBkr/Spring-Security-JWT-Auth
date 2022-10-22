package com.peaqock.aml.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExcelSheet {

    private int rowCount = 0;

    private String name;

    private List<Map<String, Object>> content = new ArrayList<>();
}
