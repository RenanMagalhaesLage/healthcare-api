package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.AppointmentAvailabilityRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentAvailabilityResponseDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.domain.entity.AppointmentAvailabilityEntity;
import com.healthcareApi.domain.entity.MedicalCenterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface MedicalCenterMapper {
    @Mapping(source = "medicalCenterId", target = "id")
    MedicalCenterEntity toEntity(MedicalCenterRequestDTO dto);

    MedicalCenterResponseDTO toResponse(MedicalCenterEntity entity);

    List<MedicalCenterResponseDTO> toResponse(List<MedicalCenterEntity> entity);

    void updateEntity(MedicalCenterRequestDTO dto, @MappingTarget MedicalCenterEntity entity);
}
