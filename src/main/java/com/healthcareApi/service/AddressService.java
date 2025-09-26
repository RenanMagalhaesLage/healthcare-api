package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import com.healthcareApi.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public AddressResponseDTO update(AddressRequestDTO dto){
        AddressEntity addressEntity = addressRepository.findById(dto.addressId()).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        AddressEntity newAddressEntity = convertDtoToEntity(dto);
        newAddressEntity.setId(dto.addressId());
        newAddressEntity.setCreationTimestamp(addressEntity.getCreationTimestamp());

        return convertEntityToDto(addressRepository.save(newAddressEntity));
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
        return AddressResponseDTO.builder()
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
