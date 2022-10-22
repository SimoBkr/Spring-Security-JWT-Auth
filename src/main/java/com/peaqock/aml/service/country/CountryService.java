package com.peaqock.aml.service.country;


import com.peaqock.aml.domain.Cities;
import com.peaqock.aml.domain.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {

    Set<Cities> findCitiesByCountryCode(String name);

    List<Country> findAllCountries();


}
