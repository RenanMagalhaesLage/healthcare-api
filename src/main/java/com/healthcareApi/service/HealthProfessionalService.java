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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HealthProfessionalService {
    private final HealthProfessionalRepository healthProfessionalRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public HealthProfessionalResponseDTO create (HealthProfessionalRequestDTO dto){
        HealthProfessionalEntity healthProfessionalEntity = convertDtoToEntity(dto);
        UserResponseDTO userResponseDTO = userService.create(dto.user());

        healthProfessionalEntity.setUser(userRepository.findById(userResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("User not found")));
        return convertEntityToDto(healthProfessionalRepository.save(healthProfessionalEntity));
    }

    public List<HealthProfessionalResponseDTO> getAll(){
        List<HealthProfessionalEntity> healthProfessionalEntityList = healthProfessionalRepository.findAll();
        List<HealthProfessionalResponseDTO> healthProfessionalResponseDTOList = new ArrayList<>();
        for (HealthProfessionalEntity entity : healthProfessionalEntityList) {
            healthProfessionalResponseDTOList.add(convertEntityToDto(entity));
        }
        return healthProfessionalResponseDTOList;
    }

    public List<HealthProfessionalResponseDTO> findBySpecialty(Integer specialty){
        SpecialtyEnum[] specialties = SpecialtyEnum.values();

        if (specialty == null || specialty < 0 || specialty >= specialties.length) {
            throw new IllegalArgumentException("Invalid specialty");
        }

        SpecialtyEnum selectedSpecialty = specialties[specialty];

        List<HealthProfessionalEntity> healthProfessionalEntityList = healthProfessionalRepository.findBySpecialty(selectedSpecialty);

        List<HealthProfessionalResponseDTO> healthProfessionalResponseDTOList = new ArrayList<>();

        for (HealthProfessionalEntity entity : healthProfessionalEntityList) {
            healthProfessionalResponseDTOList.add(convertEntityToDto(entity));
        }
        return healthProfessionalResponseDTOList;
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
