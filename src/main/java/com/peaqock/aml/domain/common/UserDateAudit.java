package com.peaqock.aml.domain.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
