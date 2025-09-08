package com.healthcareApi.domain.dto.request;

import java.util.List;

public record PrescriptionRequestDTO(String observation, List<MedicationRequestDTO> medications) {
}
