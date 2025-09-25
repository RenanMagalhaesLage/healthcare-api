package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AppointmentRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentResponseDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.entity.*;
import com.healthcareApi.repository.AppointmentRepository;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.MedicalCenterRepository;
import com.healthcareApi.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final HealthProfessionalRepository healthProfessionalRepository;
    private final HealthProfessionalService healthProfessionalService;
    private final PrescriptionService prescriptionService;
    private final PatientService patientService;

    public AppointmentResponseDTO create(AppointmentRequestDTO dto){
        AppointmentEntity appointmentEntity = convertDtoToEntity(dto);
        appointmentEntity.setMedicalCenter(medicalCenterRepository.findById(dto.medicalCenterId()).orElseThrow(() -> new EntityNotFoundException("Medical Center not found")));

        return convertEntityToDto(appointmentRepository.save(appointmentEntity));
    }

    public List<AppointmentResponseDTO> getAll(Long medicalCenterId){
        MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(medicalCenterId).orElseThrow(() -> new EntityNotFoundException("Medical Center not found"));
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findByMedicalCenter(medicalCenterEntity);
        List<AppointmentResponseDTO> appointmentResponseDTOList = new ArrayList<>();
        for (AppointmentEntity appointmentEntity : appointmentEntityList) {
            appointmentResponseDTOList.add(convertEntityToDto(appointmentEntity));
        }
        return appointmentResponseDTOList;
    }

    public List<AppointmentResponseDTO> findByHealthProfessional(Long healthProfessionalId){
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalRepository.findById(healthProfessionalId).orElseThrow(() -> new EntityNotFoundException("Health Professional not found"));
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findByHealthProfessional(healthProfessionalEntity);
        List<AppointmentResponseDTO> appointmentResponseDTOList = new ArrayList<>();
        for (AppointmentEntity appointmentEntity : appointmentEntityList) {
            appointmentResponseDTOList.add(convertEntityToDto(appointmentEntity));
        }
        return appointmentResponseDTOList;
    }

    public List<AppointmentResponseDTO> findByPatient(Long patientId){
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findByPatient(patientEntity);
        List<AppointmentResponseDTO> appointmentResponseDTOList = new ArrayList<>();
        for (AppointmentEntity appointmentEntity : appointmentEntityList) {
            appointmentResponseDTOList.add(convertEntityToDto(appointmentEntity));
        }
        return appointmentResponseDTOList;
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
