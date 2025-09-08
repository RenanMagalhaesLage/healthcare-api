package com.healthcareApi.domain.dto.response;

import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.domain.entity.PatientEntity;
import com.healthcareApi.domain.entity.PrescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private LocalDateTime date;
    private HealthProfessionalResponseDTO healthProfessional;
    private PatientResponseDTO patient;
    private PrescriptionResponseDTO prescription;
    private String observation;
}
