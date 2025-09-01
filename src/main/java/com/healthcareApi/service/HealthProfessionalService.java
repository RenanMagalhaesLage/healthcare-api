package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.UserEntity;
import com.healthcareApi.enums.GenderEnum;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthProfessionalService {
    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public HealthProfessionalResponseDTO create (HealthProfessionalRequestDTO dto){
        HealthProfessionalEntity healthProfessionalEntity = convertDtoToEntity(dto);
        UserResponseDTO userResponseDTO = userService.create(dto.user());

        healthProfessionalEntity.setUser(userRepository.findById(userResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("User not found")));
        return convertEntityToDto(healthProfessionalRepository.save(healthProfessionalEntity));
    }

    public HealthProfessionalEntity convertDtoToEntity(HealthProfessionalRequestDTO dto){
        return HealthProfessionalEntity.builder()
                .type(ProfessionalTypeEnum.values()[dto.type()])
                .specialty(SpecialtyEnum.values()[dto.specialty()])
                .professionalId(dto.professionalId())
                .build();
    }

    public HealthProfessionalResponseDTO convertEntityToDto(HealthProfessionalEntity entity){
        return HealthProfessionalResponseDTO.builder()
                .id(entity.getId())
                .user(userService.convertEntityToDto(entity.getUser()))
                .type(entity.getType())
                .specialty(entity.getSpecialty())
                .professionalId(entity.getProfessionalId())
                .build();
    }
}
