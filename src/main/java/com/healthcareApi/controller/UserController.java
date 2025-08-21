package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.create(dto));
    }
}
