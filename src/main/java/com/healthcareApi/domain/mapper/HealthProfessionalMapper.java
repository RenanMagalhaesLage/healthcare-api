package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface HealthProfessionalMapper {
    @Mapping(source = "healthProfessionalId", target = "id")
    HealthProfessionalEntity toEntity(HealthProfessionalRequestDTO dto);

    HealthProfessionalResponseDTO toResponse(HealthProfessionalEntity entity);

    List<HealthProfessionalResponseDTO> toResponse(List<HealthProfessionalEntity> entity);

    void updateEntity(HealthProfessionalRequestDTO dto, @MappingTarget HealthProfessionalEntity entity);
}
