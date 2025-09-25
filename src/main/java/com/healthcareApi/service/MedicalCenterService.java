package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.HealthProfessionalMedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.repository.AddressRepository;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.MedicalCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicalCenterService {
    private final MedicalCenterRepository medicalCenterRepository;
    private final HealthProfessionalRepository healthProfessionalRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public MedicalCenterResponseDTO create(MedicalCenterRequestDTO dto) {
        MedicalCenterEntity medicalCenterEntity = convertDtoToEntity(dto);
        AddressResponseDTO addressResponseDTO = addressService.create(dto.address());

        medicalCenterEntity.setAddress(addressRepository.findById(addressResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Address not found")));
        return convertEntityToDto(medicalCenterRepository.save(medicalCenterEntity));
    }

    public MedicalCenterResponseDTO getById(Long medicalCenterId) {
        MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(medicalCenterId) .orElseThrow(() -> new EntityNotFoundException("Medical center not found"));
        return convertEntityToDto(medicalCenterEntity);
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
