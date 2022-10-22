package com.peaqock.aml.api;

import com.peaqock.aml.domain.RapportEnity;
import com.peaqock.aml.dto.RapportDto;
import com.peaqock.aml.dto.payload.RapportResponse;
import com.peaqock.aml.service.rapport.RapportService;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rapport")
public class RapportController {

    private final RapportService rapportService;

    @PostMapping
    public RapportResponse getAllRapports(
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return rapportService.getALlReports(pageNo, pageSize, sortBy, sortDir);
    }


    @PostMapping("/create")
    public RapportEnity createRapport(@Valid @RequestBody RapportDto rapportDto) {

        return rapportService.createRapport(rapportDto);

    }

    @PostMapping("/{id}")
    public RapportEnity getRapport(@PathVariable long id) {
        return rapportService.getRapport(id);

    }


}
