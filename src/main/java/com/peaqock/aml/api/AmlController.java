package com.peaqock.aml.api;

import com.peaqock.aml.dto.payload.AmlResponse;
import com.peaqock.aml.service.aml.AmlService;
import com.peaqock.aml.utils.ObjectUtils;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aml")
@RequiredArgsConstructor
public class AmlController {

    private final AmlService service;

    @GetMapping
    public AmlResponse getAllAmlScenarios(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return service.allAmlScenarios(search, pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/comment/{id}")
    public String scenarioComment(@PathVariable(name = "id") Long id,
                                  @RequestBody String comment) {
        return service.handleScenarioComment(id, comment);
    }

    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportAllAmlScenarios() throws Exception {
        return ObjectUtils.fileResponseParser(service.convertAmlScenarios(), "AllScenarios.xlsx");
    }

    @GetMapping("/{scenario}/{subjectId}")
    public AmlResponse getAllAmlScenariosBySubjectId(
            @PathVariable(name = "scenario") String scenario,
            @PathVariable(name = "subjectId") String subjectId,
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return service.findAmlScenariosBySubjectId(scenario, subjectId, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/summaries")
    public AmlResponse getAllAmlSummaries(
            @RequestParam(name = "scenario", required = false) String scenario,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return service.allAmlSummaries(scenario, search, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/summaries/export")
    public ResponseEntity<ByteArrayResource> exportAllAmlSummaries() throws Exception {
        return ObjectUtils.fileResponseParser(service.convertAmlSummaries(), "AllSummaries.xlsx");
    }

    @GetMapping("/summaries/distinct")
    public List<Map<String, Object>> getAllAmlDistinctSummaries() {
        return service.findAmlDistinctSummaries();
    }

    @GetMapping("/details/{scenario}/{alertKey}")
    public Object getDetailByScenario(
            @PathVariable(name = "scenario") String scenario,
            @PathVariable(name = "alertKey") String alertKey
    ) {
        return service.findAmlScenarioDetail(scenario, alertKey);
    }

    @GetMapping("/transaction/{subjectId}")
    public AmlResponse getTransactionBySubjectId(
            @PathVariable(name = "subjectId") String subjectId,
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return service.findAmlTransactionBySubjectId(subjectId, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/client/{subjectId}")
    public Object getClientBySubjectId(@PathVariable(name = "subjectId") String subjectId) {
        return service.findAmlClientBySubjectId(subjectId);
    }
}
