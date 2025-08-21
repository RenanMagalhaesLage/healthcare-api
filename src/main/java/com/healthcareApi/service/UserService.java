package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import com.healthcareApi.domain.entity.UserEntity;
import com.healthcareApi.repository.AddressRepository;
import com.healthcareApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;

    public UserResponseDTO create (UserRequestDTO dto){
        UserEntity userEntity = convertDtoToEntity(dto);
        AddressResponseDTO addressResponseDTO = addressService.create(dto.address());

        userEntity.setAddress(addressRepository.findById(addressResponseDTO.getId()).get());
        return convertEntityToDto(userRepository.save(userEntity));
    }

    public UserEntity convertDtoToEntity(UserRequestDTO dto){
        return UserEntity.builder()
                .name(dto.name())
                .lastname(dto.lastname())
                .email(dto.email())
                .birthday(dto.birthday())
                .build();
    }

    public UserResponseDTO convertEntityToDto(UserEntity entity){
        return UserResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .birthday(entity.getBirthday())
                .address(addressService.convertEntityToDto(entity.getAddress()))
                .build();
    }


}
