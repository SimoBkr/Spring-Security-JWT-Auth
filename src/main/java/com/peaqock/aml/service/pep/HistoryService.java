package com.peaqock.aml.service.pep;

import com.peaqock.aml.dao.HistoryRepository;
import com.peaqock.aml.domain.History;
import com.peaqock.aml.dto.payload.FiltrageResponse;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;


    public List<History> getHistoryList() {
        return historyRepository.findAll();
    }

    public History saveHistory(History recherche) {
        return historyRepository.save(recherche);
    }

    public FiltrageResponse getHistoryListPaginated(int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = historyRepository.findAll(pageable);

        return FiltrageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(result.getContent())
                .build();
    }


}
