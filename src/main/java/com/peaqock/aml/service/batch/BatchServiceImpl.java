package com.peaqock.aml.service.batch;

import com.peaqock.aml.dao.BatchRepository;
import com.peaqock.aml.domain.Batch;
import com.peaqock.aml.dto.SearchDto;
import com.peaqock.aml.dto.payload.FiltrageResponse;
import com.peaqock.aml.exception.errors.ResourceNotFoundException;
import com.peaqock.aml.service.pep.PepService;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    private final PepService pepService;

    @Override
    public List<Batch> getBatchList() {
        return batchRepository.findAll();
    }

    @Override
    public FiltrageResponse getBatchListPaginated(int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = batchRepository.findAll(pageable);

        return FiltrageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(result.getContent())
                .build();
    }

    @Override
    public Batch getBatchById(Long batchId) {
        return batchRepository.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch not found !"));
    }

    @Override
    public Batch createBatch(Batch batchRequest) {
        return batchRepository.save(batchRequest);
    }


    @Override
    public Batch runBatch(Long batchId) {
        Batch batch = batchRepository.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch not found !"));

        batch.getJobs().stream().forEach(job -> {
            SearchDto searchDto = new SearchDto();
            searchDto.setName(job.getName());
            searchDto.setGender(job.getGender());
            searchDto.setAccuracy(batch.getSearchAccuracy());
            job.setJobResult(pepService.searchPepsForBatch(searchDto));
        });
        batch.setProcessingStatus("Processing");

        return batchRepository.save(batch);
    }

    @Override
    public ResponseEntity deleteBatch(Long batchId) {
        Batch batch = batchRepository.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch not found !"));
        batchRepository.delete(batch);
        return new ResponseEntity(HttpStatus.OK);
    }


}
