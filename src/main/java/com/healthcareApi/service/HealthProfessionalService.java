package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.mapper.HealthProfessionalMapper;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import com.healthcareApi.repository.HealthProfessionalRepository;
import com.healthcareApi.repository.MedicalCenterRepository;
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
public class HealthProfessionalService {
    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HealthProfessionalMapper healthProfessionalMapper;

    public HealthProfessionalResponseDTO findById(Long healthProfessionalId){
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalRepository.findById(healthProfessionalId).orElseThrow(() -> new EntityNotFoundException("Health Professional with id " + healthProfessionalId + " not found"));
        return healthProfessionalMapper.toResponse(healthProfessionalEntity);
    }

    public List<HealthProfessionalResponseDTO> findAll(){
        List<HealthProfessionalEntity> healthProfessionalEntityList = healthProfessionalRepository.findAll();
        return healthProfessionalMapper.toResponse(healthProfessionalEntityList);
    }

    // TODO: implementar um buscar com filtros

    public HealthProfessionalResponseDTO create (HealthProfessionalRequestDTO dto){
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalMapper.toEntity(dto);
        return healthProfessionalMapper.toResponse(healthProfessionalRepository.save(healthProfessionalEntity));
    }

    public HealthProfessionalResponseDTO update(HealthProfessionalRequestDTO dto){
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalRepository.findById(dto.healthProfessionalId()).orElseThrow(() -> new EntityNotFoundException("Health Professional with id " + dto.healthProfessionalId() + " not found"));
        healthProfessionalMapper.updateEntity(dto, healthProfessionalEntity);

        return healthProfessionalMapper.toResponse(healthProfessionalRepository.save(healthProfessionalEntity));
    }

    public String delete(Long healthProfessionalId){
        HealthProfessionalEntity healthProfessionalEntity = healthProfessionalRepository.findById(healthProfessionalId).orElseThrow(() -> new EntityNotFoundException("Health Professional with id " + healthProfessionalId + " not found"));
        healthProfessionalRepository.delete(healthProfessionalEntity);

        return "Health Professional with id " + healthProfessionalId + " deleted successfully.";
    }
}
