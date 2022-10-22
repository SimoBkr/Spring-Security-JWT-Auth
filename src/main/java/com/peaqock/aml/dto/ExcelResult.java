package com.peaqock.aml.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExcelResult {

    private long totalRow = 0;

    private long sheetSize = 0;

    private List<ExcelSheet> sheets = new ArrayList<>();
}
