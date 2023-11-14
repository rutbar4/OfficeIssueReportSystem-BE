package com.sourcery.oirs.service;

import com.sourcery.oirs.database.repository.CountryRepository;
import com.sourcery.oirs.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries (){
        return countryRepository.getAll()
                .stream()
                .map(Country::convert)
                .collect(Collectors.toList());
    }
}
