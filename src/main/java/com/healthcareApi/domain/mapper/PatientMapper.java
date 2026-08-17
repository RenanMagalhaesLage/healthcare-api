package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.domain.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PatientMapper {
    @Mapping(source = "patientId", target = "id")
    PatientEntity toEntity(PatientRequestDTO dto);

    PatientResponseDTO toResponse(PatientEntity entity);

    List<PatientResponseDTO> toResponse(List<PatientEntity> entity);

    void updateEntity(
            PatientRequestDTO dto,
            @MappingTarget PatientEntity entity
    );
}
