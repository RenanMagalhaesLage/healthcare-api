package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AppointmentAvailabilityRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentAvailabilityResponseDTO;
import com.healthcareApi.domain.entity.AppointmentAvailabilityEntity;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.domain.mapper.AppointmentAvailabilityMapper;
import com.healthcareApi.repository.AppointmentAvailabilityRepository;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.MedicalCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentAvailabilityService {
    @Autowired
    private AppointmentAvailabilityRepository appointmentAvailabilityRepository;
    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;
    @Autowired
    private MedicalCenterRepository medicalCenterRepository;
    @Autowired
    private AppointmentAvailabilityMapper appointmentAvailabilityMapper;

    public AppointmentAvailabilityResponseDTO findById(Long appointmentAvailabilityId){
        AppointmentAvailabilityEntity appointmentAvailabilityEntity = appointmentAvailabilityRepository.findById(appointmentAvailabilityId).orElseThrow(() -> new EntityNotFoundException("Appointment Availability with id " + appointmentAvailabilityId + " not found"));
        return appointmentAvailabilityMapper.toResponse(appointmentAvailabilityEntity);
    }

    public List<AppointmentAvailabilityResponseDTO> findAll(){
        List<AppointmentAvailabilityEntity> appointmentAvailabilityEntityList = appointmentAvailabilityRepository.findAll();
        return appointmentAvailabilityMapper.toResponse(appointmentAvailabilityEntityList);
    }

    public AppointmentAvailabilityResponseDTO create(AppointmentAvailabilityRequestDTO dto){
        AppointmentAvailabilityEntity appointmentAvailabilityEntity = appointmentAvailabilityMapper.toEntity(dto);

        HealthProfessionalEntity professional =
                healthProfessionalRepository.findById(dto.healthProfessionalId())
                        .orElseThrow(() -> new EntityNotFoundException("Health Professional with id " + dto.healthProfessionalId() + " not found"));

        MedicalCenterEntity medicalCenter =
                medicalCenterRepository.findById(dto.medicalCenterId())
                        .orElseThrow(() -> new EntityNotFoundException("Medical Center with id " + dto.medicalCenterId() + " not found"));


        appointmentAvailabilityEntity.setHealthProfessional(professional);
        appointmentAvailabilityEntity.setMedicalCenter(medicalCenter);
        return appointmentAvailabilityMapper.toResponse(appointmentAvailabilityRepository.save(appointmentAvailabilityEntity));
    }

    public AppointmentAvailabilityResponseDTO update(AppointmentAvailabilityRequestDTO dto){
        AppointmentAvailabilityEntity appointmentAvailabilityEntity = appointmentAvailabilityRepository.findById(dto.appointmentAvailabilityId()).orElseThrow(() -> new EntityNotFoundException("Appointment Availability with id " + dto.appointmentAvailabilityId() + " not found"));
        appointmentAvailabilityMapper.updateEntity(dto, appointmentAvailabilityEntity);

        return appointmentAvailabilityMapper.toResponse(appointmentAvailabilityRepository.save(appointmentAvailabilityEntity));
    }

    public String delete(Long appointmentAvailabilityId){
        AppointmentAvailabilityEntity appointmentAvailabilityEntity = appointmentAvailabilityRepository.findById(appointmentAvailabilityId).orElseThrow(() -> new EntityNotFoundException("Appointment Availability with id " + appointmentAvailabilityId + " not found"));
        appointmentAvailabilityRepository.delete(appointmentAvailabilityEntity);

        return "Appointment Availability with id " + appointmentAvailabilityId + " deleted successfully.";
    }
}
