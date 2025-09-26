package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.MedicationRequestDTO;
import com.healthcareApi.domain.dto.request.PrescriptionRequestDTO;
import com.healthcareApi.domain.dto.response.MedicationResponseDTO;
import com.healthcareApi.domain.dto.response.PrescriptionResponseDTO;
import com.healthcareApi.domain.entity.AppointmentEntity;
import com.healthcareApi.domain.entity.MedicationEntity;
import com.healthcareApi.domain.entity.PrescriptionEntity;
import com.healthcareApi.repository.AppointmentRepository;
import com.healthcareApi.repository.PrescriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    private final MedicationService medicationService;

    public PrescriptionResponseDTO create(PrescriptionRequestDTO dto){
        PrescriptionEntity prescriptionEntity = convertDtoToEntity(dto);
        AppointmentEntity appointmentEntity = appointmentRepository.findById(dto.appointmentId()).orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        prescriptionEntity.setAppointment(appointmentEntity);
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
