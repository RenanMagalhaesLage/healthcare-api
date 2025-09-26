package com.healthcareApi.domain.dto.request;

public record PatientRequestDTO(UserRequestDTO user, Long patientId, String bloodType) {
}
