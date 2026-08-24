package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.AppointmentAvailabilityRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentAvailabilityResponseDTO;
import com.healthcareApi.domain.entity.AppointmentAvailabilityEntity;
import com.healthcareApi.domain.mapper.AppointmentAvailabilityMapper;
import com.healthcareApi.repository.AppointmentAvailabilityRepository;
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
