package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Endpoints for managing patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok(patientService.create(dto));
    }
    @GetMapping("all")
    public ResponseEntity<List<PatientResponseDTO>> getAll(@RequestParam Long medicalCenterId){
        return ResponseEntity.ok(patientService.getAll(medicalCenterId));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long patientId){
        return ResponseEntity.ok(patientService.delete(patientId));
    }
//
//    @PutMapping()
//    public ResponseEntity<HealthProfessionalResponseDTO> update(@RequestBody HealthProfessionalRequestDTO dto){
//        return ResponseEntity.ok(patientService.update(dto));
//    }
}
