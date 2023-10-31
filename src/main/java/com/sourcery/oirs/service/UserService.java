package com.sourcery.oirs.service;


import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.exceptions.UserNotFoundException;
import com.sourcery.oirs.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(UUID id) throws UserNotFoundException {

        List<Role> roles = userRepository.getRolesById(id);

        Address address = userRepository.findUserAddressByEmployeeId(id);

        Country country = userRepository.getCountryById(address.getCountryId());

        UserEntity entity = userRepository.findById(id);

        Office office = userRepository.getOfficeByCountryId(country.getId());

        return new User(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                roles,
                address,
                country,
                office
                );
    }
}
