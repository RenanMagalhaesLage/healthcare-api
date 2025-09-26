package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.PatientEntity;
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
    private final UserService userService;
    private final UserRepository userRepository;

    public PatientResponseDTO create (PatientRequestDTO dto){
        PatientEntity patientEntity = convertDtoToEntity(dto);
        UserResponseDTO userResponseDTO = userService.create(dto.user());

        patientEntity.setUser(userRepository.findById(userResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("User not found")));
        return convertEntityToDto(patientRepository.save(patientEntity));
    }

    public List<PatientResponseDTO> getAll(Long medicalCenterId){
        List<PatientEntity> patientEntityList = patientRepository.findAll().stream().filter(n -> Objects.equals(n.getUser().getMedicalCenter().getId(), medicalCenterId)).toList();
        List<PatientResponseDTO> patientResponseDTOList = new ArrayList<>();
        for (PatientEntity entity : patientEntityList) {
            patientResponseDTOList.add(convertEntityToDto(entity));
        }
        return patientResponseDTOList;
    }

    public String delete(Long patientId){
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patientRepository.delete(patientEntity);

        return "Patient deleted successfully.";
    }

    public PatientResponseDTO update(PatientRequestDTO dto){
        PatientEntity patientEntity = patientRepository.findById(dto.patientId()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        PatientEntity newPatientEntity = convertDtoToEntity(dto);
        UserResponseDTO userResponseDTO = userService.update(dto.user());
        newPatientEntity.setId(dto.patientId());
        newPatientEntity.setCreationTimestamp(patientEntity.getCreationTimestamp());

        newPatientEntity.setUser(userRepository.findById(userResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("User not found")));
        return convertEntityToDto(patientRepository.save(newPatientEntity));
    }

    public PatientEntity convertDtoToEntity(PatientRequestDTO dto){
        return PatientEntity.builder()
                .bloodType(dto.bloodType())
                .build();
    }

    public PatientResponseDTO convertEntityToDto(PatientEntity entity){
        return PatientResponseDTO.builder()
                .id(entity.getId())
                .user(userService.convertEntityToDto(entity.getUser()))
                .bloodType(entity.getBloodType())
                .build();
    }
}
