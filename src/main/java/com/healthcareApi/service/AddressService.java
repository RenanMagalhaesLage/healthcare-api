package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import com.healthcareApi.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public AddressResponseDTO create(AddressRequestDTO dto){
        AddressEntity addressEntity = convertDtoToEntity(dto);
        return convertEntityToDto(addressRepository.save(addressEntity));
    }

    public AddressEntity convertDtoToEntity(AddressRequestDTO dto){

        return AddressEntity.builder()
                .city(dto.city())
                .complement(dto.complement())
                .country(dto.country())
                .state(dto.state())
                .number(dto.number())
                .neighborhood(dto.neighborhood())
                .street(dto.street())
                .zipCode(dto.zipCode())
                .build();
    }

    public AddressResponseDTO convertEntityToDto(AddressEntity entity){
        return new AddressResponseDTO().builder()
                .id(entity.getId())
                .city(entity.getCity())
                .complement(entity.getComplement())
                .country(entity.getCountry())
                .state(entity.getState())
                .number(entity.getNumber())
                .neighborhood(entity.getNeighborhood())
                .street(entity.getStreet())
                .zipCode(entity.getZipCode())
                .build();
    }


}
