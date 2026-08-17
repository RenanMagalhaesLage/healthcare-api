package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import com.healthcareApi.domain.mapper.AddressMapper;
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
    @Autowired
    private AddressMapper addressMapper;

    public AddressResponseDTO create(AddressRequestDTO dto){
        AddressEntity addressEntity = addressMapper.toEntity(dto);
        return addressMapper.toResponse(addressRepository.save(addressEntity));
    }

    public AddressResponseDTO update(AddressRequestDTO dto){
        AddressEntity addressEntity = addressRepository.findById(dto.addressId()).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        addressMapper.updateEntity(dto, addressEntity);

        return addressMapper.toResponse(addressRepository.save(addressEntity));
    }
}
