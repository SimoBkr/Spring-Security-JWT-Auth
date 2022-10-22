package com.peaqock.aml.dto.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peaqock.aml.shared.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private int pageNo;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private boolean last;

    @Builder.Default
    private List<UserInfo> content = new ArrayList<>();
}
