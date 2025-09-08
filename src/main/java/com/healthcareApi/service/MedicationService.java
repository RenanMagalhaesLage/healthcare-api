package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.request.MedicationRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.MedicationResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import com.healthcareApi.domain.entity.MedicationEntity;
import com.healthcareApi.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public MedicationResponseDTO create(MedicationRequestDTO dto){
        MedicationEntity medicationEntity = convertDtoToEntity(dto);
        return convertEntityToDto(medicationRepository.save(medicationEntity));
    }

    public MedicationEntity convertDtoToEntity(MedicationRequestDTO dto){

        return MedicationEntity.builder()
                .name(dto.name())
                .dosage(dto.dosage())
                .frequency(dto.frequency())
                .build();
    }

    public MedicationResponseDTO convertEntityToDto(MedicationEntity entity){
        return new MedicationResponseDTO().builder()
                .id(entity.getId())
                .name(entity.getName())
                .dosage(entity.getDosage())
                .frequency(entity.getFrequency())
                .build();
    }
}
