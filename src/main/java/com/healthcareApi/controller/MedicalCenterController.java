package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.HealthProfessionalMedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.service.MedicalCenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medical-centers")
@Tag(name = "Medical Centers", description = "Endpoints for managing medical centers")
public class MedicalCenterController {
    @Autowired
    private MedicalCenterService medicalCenterService;

    @PostMapping()
    public ResponseEntity<MedicalCenterResponseDTO> create(@RequestBody MedicalCenterRequestDTO dto){
        return ResponseEntity.ok(medicalCenterService.create(dto));
    }

    @PostMapping("/health-professionals")
    public ResponseEntity<List<MedicalCenterResponseDTO>> addHeathProfessionals(@RequestBody HealthProfessionalMedicalCenterRequestDTO dto){
        return ResponseEntity.ok(medicalCenterService.addHealthProfessionals(dto));
    }
}
