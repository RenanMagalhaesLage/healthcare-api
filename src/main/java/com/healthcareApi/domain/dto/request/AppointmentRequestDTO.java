package com.healthcareApi.domain.dto.request;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(LocalDateTime date, Long healthProfessionalId, Long patientId, String observation, Long medicalCenterId) {
}
