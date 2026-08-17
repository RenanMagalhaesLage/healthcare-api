package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.PatientEntity;
import com.healthcareApi.domain.mapper.PatientMapper;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.PatientRepository;
import com.healthcareApi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientResponseDTO findById(Long patientId){
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient with id " + patientId + " not found"));
        return patientMapper.toResponse(patientEntity);
    }

    public List<PatientResponseDTO> getAll(){
        List<PatientEntity> patientEntityList = patientRepository.findAll();
        return patientMapper.toResponse(patientEntityList);
    }

    public PatientResponseDTO create (PatientRequestDTO dto){
        PatientEntity patientEntity = patientMapper.toEntity(dto);
        return patientMapper.toResponse(patientRepository.save(patientEntity));
    }

    public PatientResponseDTO update(PatientRequestDTO dto){
        PatientEntity patientEntity = patientRepository.findById(dto.patientId()).orElseThrow(() -> new EntityNotFoundException("Patient with id " + dto.patientId() + " not found"));
        patientMapper.updateEntity(dto, patientEntity);
        return patientMapper.toResponse(patientRepository.save(patientEntity));
    }

    public String delete(Long patientId){
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient with id " + patientId + " not found"));
        patientRepository.delete(patientEntity);

        return "Patient with id " + patientId + " deleted successfully.";
    }
}
