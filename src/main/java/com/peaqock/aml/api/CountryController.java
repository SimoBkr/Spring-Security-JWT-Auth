package com.peaqock.aml.api;

import com.peaqock.aml.domain.Cities;
import com.peaqock.aml.domain.Country;
import com.peaqock.aml.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountryList() {
        return ResponseEntity.ok(countryService.findAllCountries());
    }

    @PostMapping(value = "/cities")
    public ResponseEntity<Set<Cities>> getAllCities(@RequestParam(name = "contryCode", defaultValue = "", required = false) String contryCode) {
        return ResponseEntity.ok(countryService.findCitiesByCountryCode(contryCode));
    }


}
