package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.HealthProfessionalMedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.service.HealthProfessionalService;
import com.healthcareApi.service.MedicalCenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-professionals")
@RequiredArgsConstructor
@Tag(name = "Health Professionals", description = "Endpoints for managing health professionals")
public class HealthProfessionalController {
    private final HealthProfessionalService healthProfessionalService;

    @PostMapping()
    public ResponseEntity<HealthProfessionalResponseDTO> create(@RequestBody HealthProfessionalRequestDTO dto){
        return ResponseEntity.ok(healthProfessionalService.create(dto));
    }

    @GetMapping("all")
    public ResponseEntity<List<HealthProfessionalResponseDTO>> getAll(){
        return ResponseEntity.ok(healthProfessionalService.getAll());
    }

    @GetMapping("by-specialty")
    public ResponseEntity<List<HealthProfessionalResponseDTO>> findBySpecialty(@RequestParam Integer specialty){
        return ResponseEntity.ok(healthProfessionalService.findBySpecialty(specialty));
    }
}
