package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import com.healthcareApi.domain.mapper.MedicalCenterMapper;
import com.healthcareApi.repository.MedicalCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MedicalCenterService {
    @Autowired
    private MedicalCenterRepository medicalCenterRepository;

    @Autowired
    private MedicalCenterMapper medicalCenterMapper;

    public MedicalCenterResponseDTO findById(Long medicalCenterId) {
        MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(medicalCenterId) .orElseThrow(() -> new EntityNotFoundException("Medical Center with id " + medicalCenterId + " not found"));
        return medicalCenterMapper.toResponse(medicalCenterEntity);
    }

    public List<MedicalCenterResponseDTO> findAll(){
        List<MedicalCenterEntity> medicalCenterEntityList = medicalCenterRepository.findAll();
        return medicalCenterMapper.toResponse(medicalCenterEntityList);
    }

    public MedicalCenterResponseDTO create(MedicalCenterRequestDTO dto) {
        MedicalCenterEntity medicalCenterEntity = medicalCenterMapper.toEntity(dto);
        return medicalCenterMapper.toResponse(medicalCenterEntity);
    }

    public MedicalCenterResponseDTO update(MedicalCenterRequestDTO dto){
        MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(dto.medicalCenterId()) .orElseThrow(() -> new EntityNotFoundException("Medical Center with id " + dto.medicalCenterId() + " not found"));
        medicalCenterMapper.updateEntity(dto, medicalCenterEntity);
        return medicalCenterMapper.toResponse(medicalCenterEntity);
    }

    public String delete(Long medicalCenterId){
        MedicalCenterEntity medicalCenterEntity = medicalCenterRepository.findById(medicalCenterId) .orElseThrow(() -> new EntityNotFoundException("Medical Center with id " + medicalCenterId + " not found"));
        medicalCenterRepository.delete(medicalCenterEntity);
        return "Medical Center with id " + medicalCenterId + " deleted successfully.";
    }




}
