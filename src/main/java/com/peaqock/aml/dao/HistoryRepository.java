package com.peaqock.aml.dao;

import com.peaqock.aml.domain.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, Long> {

    @Override
    Page<History> findAll(Pageable pageable);
}
