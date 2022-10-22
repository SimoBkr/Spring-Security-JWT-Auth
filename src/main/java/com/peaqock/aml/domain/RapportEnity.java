package com.peaqock.aml.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rapport")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RapportEnity {

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
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "searched_name")
    private String searchedName;

    @Column(name = "flag")
    private String flag;

    @Column(name = "status")
    private String status;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Commentaire comment;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pep_id")
    private Pep pep;

}
