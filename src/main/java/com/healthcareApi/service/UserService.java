package com.healthcareApi.service;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.domain.entity.UserEntity;
import com.healthcareApi.domain.mapper.UserMapper;
import com.healthcareApi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO create(UserRequestDTO dto){
        UserEntity userEntity = userMapper.toEntity(dto);
        return userMapper.toResponse(userRepository.save(userEntity));
    }

    public UserResponseDTO update(UserRequestDTO dto){
        UserEntity userEntity = userRepository.findById(dto.userId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userMapper.updateEntity(dto, userEntity);

        return userMapper.toResponse(userRepository.save(userEntity));
    }

    public List<UserResponseDTO> findAll(){
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toResponse(userEntityList);
    }
}
