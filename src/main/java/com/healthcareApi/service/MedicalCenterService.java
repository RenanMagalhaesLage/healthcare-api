package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.repository.AddressRepository;
import com.healthcareApi.repository.MedicalCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MedicalCenterService {
    @Autowired
    private MedicalCenterRepository medicalCenterRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;

    public MedicalCenterResponseDTO create(MedicalCenterRequestDTO dto) {
        MedicalCenterEntity medicalCenterEntity = convertDtoToEntity(dto);
        AddressResponseDTO addressResponseDTO = addressService.create(dto.address());

        medicalCenterEntity.setAddress(addressRepository.findById(addressResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Address not found")));
        return convertEntityToDto(medicalCenterRepository.save(medicalCenterEntity));
    }

    public MedicalCenterEntity convertDtoToEntity(MedicalCenterRequestDTO dto){
        return MedicalCenterEntity.builder()
                .name(dto.name())
                .phone(dto.phone())
                .build();
    }

    public MedicalCenterResponseDTO convertEntityToDto(MedicalCenterEntity entity){
        return MedicalCenterResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .address(addressService.convertEntityToDto(entity.getAddress()))
                .build();
    }

}
