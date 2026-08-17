package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.UserRequestDTO;
import com.healthcareApi.domain.dto.response.UserResponseDTO;
import com.healthcareApi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(params = "userId")
    public ResponseEntity<UserResponseDTO> findById(@RequestParam Long userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long userId){
        return ResponseEntity.ok(userService.delete(userId));
    }
}
