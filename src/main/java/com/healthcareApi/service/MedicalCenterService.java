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
public class MedicalCenterService {
    @Autowired
    private MedicalCenterRepository medicalCenterRepository;
    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;
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

    public List<MedicalCenterResponseDTO> addHealthProfessionals(HealthProfessionalMedicalCenterRequestDTO dto) {
        List<MedicalCenterResponseDTO> dtos = new ArrayList<MedicalCenterResponseDTO>();
        for(Long id : dto.medicalCenterIds()) {
            MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Medical center not found"));

            Set<HealthProfessionalEntity> healthProfessionalEntitySet = new HashSet<>(healthProfessionalRepository.findAllById(dto.healthProfessionalIds()));

            medicalCenterEntity.getHealthProfessionals().addAll(healthProfessionalEntitySet);
            MedicalCenterEntity medicalCenterEntityAux =  medicalCenterRepository.saveAndFlush(medicalCenterEntity);
            for (HealthProfessionalEntity professionalEntity : healthProfessionalEntitySet) {
                professionalEntity.getMedicalCenters().add(medicalCenterEntityAux);
                healthProfessionalRepository.save(professionalEntity);
            }
            dtos.add(convertEntityToDto(medicalCenterEntityAux));
        }

        return dtos;
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
                .healthProfessionalSet(entity.getHealthProfessionals())
                .build();
    }

}
