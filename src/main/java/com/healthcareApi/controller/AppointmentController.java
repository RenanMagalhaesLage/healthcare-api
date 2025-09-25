package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.AppointmentRequestDTO;
import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentResponseDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.service.AppointmentService;
import com.healthcareApi.service.HealthProfessionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointment", description = "Endpoints for managing appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    @GetMapping()
    public ResponseEntity<List<AppointmentResponseDTO>> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("by-health-professional")
    public ResponseEntity<List<AppointmentResponseDTO>> findByHealthProfessional(@RequestParam Long healthProfessionalId){
        return ResponseEntity.ok(appointmentService.findByHealthProfessional(healthProfessionalId));
    }

    @GetMapping("by-patient")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@RequestParam Long patientId){
        return ResponseEntity.ok(appointmentService.findByPatient(patientId));
    }
}
