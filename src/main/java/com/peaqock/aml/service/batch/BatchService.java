package com.peaqock.aml.service.batch;

import com.peaqock.aml.domain.Batch;
import com.peaqock.aml.dto.payload.FiltrageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BatchService {

    List<Batch> getBatchList();

    FiltrageResponse getBatchListPaginated(int pageNo, int pageSize, String sortBy, String sortDir);


    Batch getBatchById(Long batchId);

    Batch createBatch(Batch batchRequest);

    Batch runBatch(Long batchId);

    ResponseEntity deleteBatch(Long batchId);
}
