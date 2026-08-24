package com.healthcareApi.domain.dto.request;

import java.math.BigDecimal;

public record PatientRequestDTO(UserRequestDTO user, Long patientId, String bloodType, BigDecimal height, BigDecimal weight) {
}
