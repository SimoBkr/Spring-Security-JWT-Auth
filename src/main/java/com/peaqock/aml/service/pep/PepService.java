package com.peaqock.aml.service.pep;

import com.peaqock.aml.dao.PepRepository;
import com.peaqock.aml.domain.History;
import com.peaqock.aml.domain.Pep;
import com.peaqock.aml.dto.SearchDto;
import com.peaqock.aml.dto.enums.PepType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PepService {
    private final PepRepository pepRepository;
    private final HistoryService historyService;


    public String saveAllPeps(List<Pep> peps) {
        pepRepository.saveAll(peps);
        return "Peps Saved successfully ! ";
    }

    public List<Pep> search(SearchDto searchDto) {
//        Pageable limit = PageRequest.of(0, 10);

        List<Pep> peps = pepRepository
                .getAllBySearch(searchDto.getName(),
                        searchDto.getAccuracy(),
                        searchDto.getGender(),
                        searchDto.getCin(),
                        searchDto.getType().name(),
                        searchDto.getCountry(),
                        searchDto.getCity(),
                        searchDto.getPepTypes().stream().map(x ->  x.label.equals("SANCTIONS") ? "SANCTION" : x.label ).collect(Collectors.toList()));

        History history = new History();
        history.setKeyword(searchDto.getName());
        history.setSearchedPeps(peps);

        if (peps != null && !peps.isEmpty()) {
            historyService.saveHistory(history);
        }

        return peps;
    }


    public List<Pep> searchPepsForBatch(SearchDto searchDto) {

        return pepRepository
                .getAllBySearchForBatch(searchDto.getName(),
                        5,
                        searchDto.getGender()
                       );
    }

}
