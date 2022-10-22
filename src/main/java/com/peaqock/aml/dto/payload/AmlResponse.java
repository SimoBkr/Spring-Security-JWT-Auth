package com.peaqock.aml.dto.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmlResponse {

    private int pageNo;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private boolean last;

    private Object content;

    private Object graphs;
}
