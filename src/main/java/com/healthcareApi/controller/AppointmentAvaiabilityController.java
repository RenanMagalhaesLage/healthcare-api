package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.AppointmentAvailabilityRequestDTO;
import com.healthcareApi.domain.dto.request.MedicalCenterRequestDTO;
import com.healthcareApi.domain.dto.response.AppointmentAvailabilityResponseDTO;
import com.healthcareApi.domain.dto.response.MedicalCenterResponseDTO;
import com.healthcareApi.service.AppointmentAvailabilityService;
import com.healthcareApi.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment-availabilities")
@Tag(name = "Appointment Availability", description = "Endpoints for managing appointment availabilities")
public class AppointmentAvaiabilityController {
    @Autowired
    private AppointmentAvailabilityService appointmentAvailabilityService;

    @GetMapping()
    public ResponseEntity<List<AppointmentAvailabilityResponseDTO>> findAll(){
        return ResponseEntity.ok(appointmentAvailabilityService.findAll());
    }

    @GetMapping(params = "medicalCenterId")
    public ResponseEntity<AppointmentAvailabilityResponseDTO> findById(@RequestParam Long appointmentAvailabilityId){
        return ResponseEntity.ok(appointmentAvailabilityService.findById(appointmentAvailabilityId));
    }

    @PostMapping()
    public ResponseEntity<AppointmentAvailabilityResponseDTO> create(@RequestBody AppointmentAvailabilityRequestDTO dto){
        return ResponseEntity.ok(appointmentAvailabilityService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<AppointmentAvailabilityResponseDTO> update(@RequestBody AppointmentAvailabilityRequestDTO dto){
        return ResponseEntity.ok(appointmentAvailabilityService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long appointmentAvailabilityId){
        return ResponseEntity.ok(appointmentAvailabilityService.delete(appointmentAvailabilityId));
    }

}
