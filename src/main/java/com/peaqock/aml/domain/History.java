package com.peaqock.aml.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String keyword;

    @ManyToMany
    @JoinTable(
            name = "history_peps",
            joinColumns = @JoinColumn(name = "history_id"),
            inverseJoinColumns = @JoinColumn(name = "pep_id")
    )
    private List<Pep> searchedPeps;


}
