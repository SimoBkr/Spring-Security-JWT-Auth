package com.peaqock.aml.dao;

import com.peaqock.aml.domain.RapportEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepository extends JpaRepository<RapportEnity, Long> {
}
