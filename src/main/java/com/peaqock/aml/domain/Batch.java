package com.peaqock.aml.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "batches")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "batche_name")
    private String batchName;

    @Column(name = "code_senario")
    private String scenarioCode;

    @Column(name = "description")
    private String description;

    @Column(name = "exclude_common")
    private Boolean excludeCommon;

    @Column(name = "search_type")
    private String searchType;

    @Column(name = "search_accuracy")
    private int searchAccuracy;

    @Column(name = "processing_status", columnDefinition = "varchar(255) default 'Inactive'")
    private String processingStatus;

    @Column(name = "reviewer_status", columnDefinition = "varchar(255) default 'Valid'")
    private String reviewerStatus;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Job> jobs;


}
