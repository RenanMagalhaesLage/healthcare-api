package com.healthcareApi.domain.mapper;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {
    @Mapping(source = "userId", target = "id")
    UserEntity toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(UserEntity entity);

    List<UserResponseDTO> toResponse(List<UserEntity> entities);

    void updateEntity(UserRequestDTO dto, @MappingTarget UserEntity entity);

}
