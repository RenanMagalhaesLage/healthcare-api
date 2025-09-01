package com.healthcareApi.domain.dto.request;

import java.util.List;

public record HealthProfessionalMedicalCenterRequestDTO(List<Long> medicalCenterIds, List<Long> healthProfessionalIds) {
}
