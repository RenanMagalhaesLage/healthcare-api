package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "addressId", target = "id")
    AddressEntity toEntity(AddressRequestDTO dto);

    AddressResponseDTO toResponse(AddressEntity entity);

    void updateEntity(AddressRequestDTO dto, @MappingTarget AddressEntity entity);
}
