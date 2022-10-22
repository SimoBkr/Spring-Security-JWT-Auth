package com.peaqock.aml.service.rapport;

import com.peaqock.aml.domain.RapportEnity;
import com.peaqock.aml.dto.RapportDto;
import com.peaqock.aml.dto.payload.RapportResponse;

public interface RapportService {

    RapportResponse getALlReports(int pageNo, int pageSize, String sortBy, String sortDir);

    RapportEnity createRapport(RapportDto rapport);


    RapportEnity getRapport(long rapportId);

    void deleteRapport(long rappordId);
}
