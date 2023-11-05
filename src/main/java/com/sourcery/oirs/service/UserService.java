package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.AddressEntity;
import com.sourcery.oirs.database.entity.CountryEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.exceptions.AddressNotFoundException;
import com.sourcery.oirs.exceptions.CountryNotFoundException;
import com.sourcery.oirs.exceptions.OfficeNotFoundException;
import com.sourcery.oirs.exceptions.UserNotFoundException;
import com.sourcery.oirs.model.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Builder
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(UUID id) throws UserNotFoundException {

        AddressEntity addressEntity = userRepository.findUserAddressByEmployeeId(id).orElseThrow(() -> new AddressNotFoundException(String.format("User %s has no address", id)));

        CountryEntity countryEntity = userRepository.getCountryById(addressEntity.getCountryId()).orElseThrow(() -> new CountryNotFoundException("User has no country assigned"));

        UserEntity entity = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(String.format("User %s not found", id)));

        OfficeEntity officeEntity = userRepository.getOfficeByCountryId(countryEntity.getId()).orElseThrow(() -> new OfficeNotFoundException("User has no office assigned"));

        return User.convert(entity,countryEntity, addressEntity, officeEntity);
    }
}
