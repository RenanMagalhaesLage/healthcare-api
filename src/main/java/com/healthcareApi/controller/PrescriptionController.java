package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.PrescriptionRequestDTO;
import com.healthcareApi.domain.dto.response.PrescriptionResponseDTO;
import com.healthcareApi.service.PrescriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
@Tag(name = "Prescriptions", description = "Endpoints for managing prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping()
    public ResponseEntity<PrescriptionResponseDTO> create(@RequestBody PrescriptionRequestDTO dto){
        return ResponseEntity.ok(prescriptionService.create(dto));
    }

}
