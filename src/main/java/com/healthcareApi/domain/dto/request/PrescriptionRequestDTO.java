package com.healthcareApi.domain.dto.request;

import java.util.List;

public record PrescriptionRequestDTO(Long appointmentId, String observation, List<MedicationRequestDTO> medications) {
}
