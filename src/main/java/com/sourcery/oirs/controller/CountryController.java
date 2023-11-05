package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.Country;
import com.sourcery.oirs.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getAllCountries () {
        return countryService.getAllCountries();
    }

}
