package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.AddressEntity;
import com.sourcery.oirs.database.entity.CountryEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.AddressRepository;
import com.sourcery.oirs.database.repository.CountryRepository;
import com.sourcery.oirs.database.repository.OfficeRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.exceptions.AddressNotFoundException;
import com.sourcery.oirs.exceptions.CountryNotFoundException;
import com.sourcery.oirs.exceptions.OfficeNotFoundException;
import com.sourcery.oirs.exceptions.UserNotFoundException;
import com.sourcery.oirs.model.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Builder
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final CountryRepository countryRepository;
    private final AddressRepository addressRepository;

    public User getUserById(UUID id) throws UserNotFoundException {
        AddressEntity addressEntity = addressRepository.findUserAddressByEmployeeId(id)
                .orElseThrow(() -> new AddressNotFoundException(String.format("User %s has no address", id)));
        CountryEntity countryEntity = countryRepository.getCountryById(addressEntity.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException("User has no country assigned"));
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(String.format("User %s not found", id)));
        OfficeEntity officeEntity = getOfficeByUser(id);
        return User.convert(entity,countryEntity, addressEntity, officeEntity);
    }

    public OfficeEntity getOfficeByUser (UUID userId){
        UUID officeId = officeRepository.getOfficeIdByEmployeeId(userId)
                .orElseThrow(()-> new OfficeNotFoundException(String.format("user %S has no office assigned", userId)));
        return officeRepository.getOfficeById(officeId)
                .orElseThrow(() -> new OfficeNotFoundException(String.format("Office %s was not found", officeId)));

    }


    public List<UserMainInfoResponseDTO> getUserList() {
        return userRepository.getAllUsers();
    }

    public void updateUser(UserUpdateDTO userUpdate){

        UserEntity user = userRepository.findById(userUpdate.getId())
                .orElseThrow(()-> new UserNotFoundException(String.format("User %s not found", userUpdate.getId())));
        user.setAvatar(userUpdate.getAvatar());
        userRepository.updateAvatar(user);

        AddressEntity userAddress = addressRepository.findUserAddressByEmployeeId(userUpdate.getId())
                .orElseThrow(() -> new AddressNotFoundException(String.format("employer %S address not found", userUpdate.getId())));
        userAddress.setStreet(userUpdate.getAddress().getStreet());
        userAddress.setCity(userUpdate.getAddress().getCity());
        userAddress.setState(userUpdate.getAddress().getState());
        userAddress.setPostcode(userUpdate.getAddress().getPostcode());
        userAddress.setCountryId(userUpdate.getAddress().getCountryId());
        addressRepository.update(userAddress);
    }
}
