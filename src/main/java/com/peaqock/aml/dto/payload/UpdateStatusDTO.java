package com.peaqock.aml.dto.payload;

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
public class UpdateStatusDTO {

    private FeedbackStatus status;

}

