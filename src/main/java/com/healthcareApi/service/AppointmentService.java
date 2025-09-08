package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AppointmentRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentResponseDTO;
import com.healthcareApi.domain.entity.*;
import com.healthcareApi.repository.AppointmentRepository;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;
    @Autowired
    private HealthProfessionalService healthProfessionalService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private PatientService patientService;

    public AppointmentResponseDTO create(AppointmentRequestDTO dto){
        AppointmentEntity appointmentEntity = convertDtoToEntity(dto);
        return convertEntityToDto(appointmentRepository.save(appointmentEntity));
    }

    public AppointmentEntity convertDtoToEntity(AppointmentRequestDTO dto){
        PatientEntity patientEntity = patientRepository.findById(dto.patientId()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalRepository.findById(dto.healthProfessionalId()).orElseThrow(() -> new EntityNotFoundException("Health Professional not found"));
        return AppointmentEntity.builder()
                .date(dto.date())
                .patient(patientEntity)
                .healthProfessional(healthProfessionalEntity)
                .observation(dto.observation())
                .build();
    }

    public AppointmentResponseDTO convertEntityToDto(AppointmentEntity entity) {

        return new AppointmentResponseDTO(
                entity.getId(),
                entity.getDate(),
                healthProfessionalService.convertEntityToDto(entity.getHealthProfessional()),
                patientService.convertEntityToDto(entity.getPatient()),
                prescriptionService.convertEntityToDto(entity.getPrescription()),
                entity.getObservation()

        );
    }
}
