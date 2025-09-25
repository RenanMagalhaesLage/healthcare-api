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
