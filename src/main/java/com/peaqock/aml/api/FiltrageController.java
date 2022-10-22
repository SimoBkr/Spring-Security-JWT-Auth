package com.peaqock.aml.api;

import com.peaqock.aml.domain.Pep;
import com.peaqock.aml.dto.SearchDto;
import com.peaqock.aml.service.pep.PepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filtrage")
public class FiltrageController {
    private final PepService pepService;


    @PostMapping(value = "/saveall")
    public ResponseEntity<String> saveAllPeps(List<Pep> peps) {
        return ResponseEntity.ok(pepService.saveAllPeps(peps));

    }


    @PostMapping("")
    public List<Pep> searchPeps(@Valid @RequestBody SearchDto searchDto) {
        return pepService.search(searchDto);
    }


}
