package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.AppointmentAvailabilityRequestDTO;
import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentAvailabilityResponseDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.entity.AppointmentAvailabilityEntity;
import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AppointmentAvailabilityMapper {
    @Mapping(source = "appointmentAvailabilityId", target = "id")
    AppointmentAvailabilityEntity toEntity(AppointmentAvailabilityRequestDTO dto);

    AppointmentAvailabilityResponseDTO toResponse(AppointmentAvailabilityEntity entity);

    List<AppointmentAvailabilityResponseDTO> toResponse(List<AppointmentAvailabilityEntity> entity);

    void updateEntity(AppointmentAvailabilityRequestDTO dto, @MappingTarget AppointmentAvailabilityEntity entity);
}
