package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.UserEntity;
import com.healthcareApi.enums.GenderEnum;
import com.healthcareApi.repository.AddressRepository;
import com.healthcareApi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public UserResponseDTO create (UserRequestDTO dto){
        UserEntity userEntity = convertDtoToEntity(dto);
        AddressResponseDTO addressResponseDTO = addressService.create(dto.address());

        userEntity.setAddress(addressRepository.findById(addressResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Address not found")));
        return convertEntityToDto(userRepository.save(userEntity));
    }

    public List<UserResponseDTO> findAll(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userResponseDTOs.add(convertEntityToDto(userEntity));
        }
        return userResponseDTOs;
    }

    public UserEntity convertDtoToEntity(UserRequestDTO dto){
        return UserEntity.builder()
                .name(dto.name())
                .lastname(dto.lastname())
                .email(dto.email())
                .birthday(dto.birthday())
                .phone(dto.phone())
                .gender(GenderEnum.values()[dto.gender()])
                .build();
    }

    public UserResponseDTO convertEntityToDto(UserEntity entity){
        return UserResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .birthday(entity.getBirthday())
                .phone(entity.getPhone())
                .gender(entity.getGender())
                .address(addressService.convertEntityToDto(entity.getAddress()))
                .build();
    }


}
