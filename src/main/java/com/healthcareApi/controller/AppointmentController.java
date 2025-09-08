package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.AppointmentRequestDTO;
import com.healthcareApi.domain.dto.request.HealthProfessionalRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentResponseDTO;
import com.healthcareApi.domain.dto.response.HealthProfessionalResponseDTO;
import com.healthcareApi.service.AppointmentService;
import com.healthcareApi.service.HealthProfessionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Appointment", description = "Endpoints for managing appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.ok(appointmentService.create(dto));
    }
}
