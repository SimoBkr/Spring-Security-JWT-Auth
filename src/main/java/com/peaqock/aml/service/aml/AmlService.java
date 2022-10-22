package com.peaqock.aml.service.aml;

import com.peaqock.aml.dto.payload.AmlResponse;

import java.util.List;
import java.util.Map;

public interface AmlService {
    AmlResponse allAmlScenarios(String search, int pageNo, int pageSize, String sortBy, String sortDir);

    String handleScenarioComment(Long id, String comment);

    byte[] convertAmlScenarios() throws Exception;

    byte[] convertAmlSummaries() throws Exception;

    AmlResponse allAmlSummaries(String scenario, String search, int pageNo, int pageSize, String sortBy, String sortDir);

    List<Map<String, Object>> findAmlDistinctSummaries();

    Object findAmlClientBySubjectId(String subjectId);

    Object findAmlScenarioDetail(String scenario, String alertKey);

    AmlResponse findAmlTransactionBySubjectId(String subjectId, int pageNo, int pageSize, String sortBy, String sortDir);

    AmlResponse findAmlScenariosBySubjectId(String scenario, String subjectId, int pageNo, int pageSize, String sortBy, String sortDir);
}
