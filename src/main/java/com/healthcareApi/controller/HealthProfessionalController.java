package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.service.HealthProfessionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-professionals")
@RequiredArgsConstructor
@Tag(name = "Health Professionals", description = "Endpoints for managing health professionals")
public class HealthProfessionalController {
    private final HealthProfessionalService healthProfessionalService;

    @GetMapping()
    public ResponseEntity<List<HealthProfessionalResponseDTO>> findAll(){
        return ResponseEntity.ok(healthProfessionalService.findAll());
    }

    @GetMapping(params = "healthProfessionalId")
    public ResponseEntity<HealthProfessionalResponseDTO> findById(@RequestParam Long healthProfessionalId){
        return ResponseEntity.ok(healthProfessionalService.findById(healthProfessionalId));
    }

    @PostMapping()
    public ResponseEntity<HealthProfessionalResponseDTO> create(@RequestBody HealthProfessionalRequestDTO dto){
        return ResponseEntity.ok(healthProfessionalService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<HealthProfessionalResponseDTO> update(@RequestBody HealthProfessionalRequestDTO dto){
        return ResponseEntity.ok(healthProfessionalService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long healthProfessionalId){
        return ResponseEntity.ok(healthProfessionalService.delete(healthProfessionalId));
    }


}
