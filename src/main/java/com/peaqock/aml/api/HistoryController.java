package com.peaqock.aml.api;

import com.peaqock.aml.domain.History;
import com.peaqock.aml.dto.payload.FiltrageResponse;
import com.peaqock.aml.service.pep.HistoryService;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("")
    public List<History> getHistory() {
        return historyService.getHistoryList();
    }

    @PostMapping("/paginated")
    public FiltrageResponse getAllBatches(
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return historyService.getHistoryListPaginated(pageNo, pageSize, sortBy, sortDir);
    }


}
