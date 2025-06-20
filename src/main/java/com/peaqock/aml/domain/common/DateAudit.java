package com.peaqock.aml.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(
        value = {"creationDate", "updatedDate"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "created_on", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creationDate;

    @LastModifiedDate
    @Column(name = "updated_on", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedDate;
}
