package com.peaqock.aml.dao;

import com.peaqock.aml.domain.AmlScenarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmlScenarioRepository extends JpaRepository<AmlScenarioEntity, Long> {

    @Query("from AmlScenarioEntity a where " +
            "1 = case when lower(a.scenarioName) like lower(concat('%', :search,'%')) or :search is null then 1 else 0 end"
    )
    Page<AmlScenarioEntity> findAllBySearch(String search, Pageable pageable);

    Page<AmlScenarioEntity> findAllBySubjectIdAndScenarioCode(String subjectId, String scenario, Pageable pageable);
}
