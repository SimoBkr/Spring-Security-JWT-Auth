package com.peaqock.aml.dto;


import com.peaqock.aml.dto.enums.FeedbackStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {


    private UUID scriptId;

    private Object feedback;

    private UUID prospectId;

    private FeedbackStatus status;

    private Long score;

}
