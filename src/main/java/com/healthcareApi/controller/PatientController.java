package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Endpoints for managing patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientResponseDTO>> findAll(){
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping(params = "patientId")
    public ResponseEntity<PatientResponseDTO> findById(@RequestParam Long patientId){
        return ResponseEntity.ok(patientService.findById(patientId));
    }

    @PostMapping()
    public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok(patientService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<PatientResponseDTO> update(@RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok(patientService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long patientId){
        return ResponseEntity.ok(patientService.delete(patientId));
    }
}
