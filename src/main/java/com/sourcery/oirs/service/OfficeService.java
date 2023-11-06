package com.sourcery.oirs.service;

import com.sourcery.oirs.database.repository.OfficeRepository;
import com.sourcery.oirs.model.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeRepository officeRepository;

    public List<Office> getAllOffices() {
        return officeRepository.getAll()
                .stream()
                .map(Office::convert)
                .collect(Collectors.toList());
    }

}
