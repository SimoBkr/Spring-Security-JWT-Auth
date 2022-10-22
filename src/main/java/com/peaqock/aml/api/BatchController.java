package com.peaqock.aml.api;

import com.peaqock.aml.domain.Batch;
import com.peaqock.aml.dto.payload.FiltrageResponse;
import com.peaqock.aml.service.batch.BatchServiceImpl;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchController {

    private final BatchServiceImpl batchService;

    @GetMapping("/one")
    public List<Batch> geBatchById() {
        return batchService.getBatchList();
    }

    @GetMapping("")
    public List<Batch> geBatches() {
        return batchService.getBatchList();
    }

    @PostMapping("/paginated")
    public FiltrageResponse getAllBatches(
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return batchService.getBatchListPaginated(pageNo, pageSize, sortBy, sortDir);
    }


    @PostMapping("")
    public Batch createBatch(@Valid @RequestBody Batch batch) {
        return batchService.createBatch(batch);
    }


    @PutMapping("/run/{id}")
    public Batch runBatch(@PathVariable long id) {
        return batchService.runBatch(id);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBatch(@PathVariable long id) {
        return batchService.deleteBatch(id);
    }


}
