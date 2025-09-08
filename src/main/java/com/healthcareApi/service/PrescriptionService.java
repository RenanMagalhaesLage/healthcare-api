package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.MedicationRequestDTO;
import com.healthcareApi.domain.dto.request.PrescriptionRequestDTO;
import com.healthcareApi.domain.dto.response.MedicationResponseDTO;
import com.healthcareApi.domain.dto.response.PrescriptionResponseDTO;
import com.healthcareApi.domain.entity.MedicationEntity;
import com.healthcareApi.domain.entity.PrescriptionEntity;
import com.healthcareApi.repository.PrescriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private MedicationService medicationService;

    public PrescriptionResponseDTO create(PrescriptionRequestDTO dto){
        PrescriptionEntity prescriptionEntity = convertDtoToEntity(dto);
        List<MedicationEntity> medicationEntityList = dto.medications().stream()
                .map(medicationDto -> {
                    MedicationEntity medicationEntity = medicationService.convertDtoToEntity(medicationDto);
                    medicationEntity.setPrescription(prescriptionEntity);
                    return medicationEntity;
                })
                .collect(Collectors.toList());
        prescriptionEntity.setMedications(medicationEntityList);
        return convertEntityToDto(prescriptionRepository.save(prescriptionEntity));
    }

    public PrescriptionEntity convertDtoToEntity(PrescriptionRequestDTO dto){

        return PrescriptionEntity.builder()
                .observation(dto.observation())
                .build();
    }

    public PrescriptionResponseDTO convertEntityToDto(PrescriptionEntity entity) {
        List<MedicationResponseDTO> medications = entity.getMedications().stream()
                .map(medicationEntity -> new MedicationResponseDTO(
                        medicationEntity.getId(),
                        medicationEntity.getName(),
                        medicationEntity.getDosage(),
                        medicationEntity.getFrequency()
                ))
                .collect(Collectors.toList());

        return new PrescriptionResponseDTO(
                entity.getId(),
                entity.getObservation(),
                medications
        );
    }
}
