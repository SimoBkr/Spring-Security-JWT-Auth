package com.peaqock.aml.dao;

import com.peaqock.aml.domain.AmlSummaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmlSummaryRepository extends JpaRepository<AmlSummaryEntity, Long> {

    @Query("from AmlSummaryEntity a where " +
            "1 = case when a.scenarioCode = :scenario or :scenario is null then 1 else 0 end " +
            "and 1 = case when lower(a.scenarioName) like lower(concat('%', :search,'%')) or :search is null then 1 else 0 end"
    )
    Page<AmlSummaryEntity> findAllBySearch(String scenario, String search, Pageable pageable);
}
