package com.peaqock.aml.service.country;

import com.peaqock.aml.dao.CountryRepository;
import com.peaqock.aml.domain.Cities;
import com.peaqock.aml.domain.Country;
import com.peaqock.aml.exception.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Set<Cities> findCitiesByCountryCode(String name) {
        Country country = countryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Country not found !"));
        return country.getCities();
    }

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }


}
