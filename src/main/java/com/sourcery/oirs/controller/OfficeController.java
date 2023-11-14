package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.Office;
import com.sourcery.oirs.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Office> getAllOffices(){
        return officeService.getAllOffices();
    }
}
