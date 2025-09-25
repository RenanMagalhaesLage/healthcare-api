package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.HealthProfessionalMedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.service.MedicalCenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-centers")
@RequiredArgsConstructor
@Tag(name = "Medical Centers", description = "Endpoints for managing medical centers")
public class MedicalCenterController {
    private final MedicalCenterService medicalCenterService;

    @PostMapping()
    public ResponseEntity<MedicalCenterResponseDTO> create(@RequestBody MedicalCenterRequestDTO dto){
        return ResponseEntity.ok(medicalCenterService.create(dto));
    }

    @GetMapping
    public ResponseEntity<MedicalCenterResponseDTO> getById(@RequestParam Long medicalCenterId){
        return ResponseEntity.ok(medicalCenterService.getById(medicalCenterId));
    }
}
