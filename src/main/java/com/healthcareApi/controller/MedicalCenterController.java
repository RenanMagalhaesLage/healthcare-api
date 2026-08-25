package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.HealthProfessionalMedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.service.MedicalCenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-centers")
@Tag(name = "Medical Centers", description = "Endpoints for managing medical centers")
public class MedicalCenterController {
    @Autowired
    private MedicalCenterService medicalCenterService;

    @GetMapping()
    public ResponseEntity<List<MedicalCenterResponseDTO>> findAll(){
        return ResponseEntity.ok(medicalCenterService.findAll());
    }

    @GetMapping(params = "medicalCenterId")
    public ResponseEntity<MedicalCenterResponseDTO> findById(@RequestParam Long medicalCenterId){
        return ResponseEntity.ok(medicalCenterService.findById(medicalCenterId));
    }

    @PostMapping()
    public ResponseEntity<MedicalCenterResponseDTO> create(@RequestBody MedicalCenterRequestDTO dto){
        return ResponseEntity.ok(medicalCenterService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<MedicalCenterResponseDTO> update(@RequestBody MedicalCenterRequestDTO dto){
        return ResponseEntity.ok(medicalCenterService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long medicalCenterId){
        return ResponseEntity.ok(medicalCenterService.delete(medicalCenterId));
    }

}
