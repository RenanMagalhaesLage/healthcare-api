package com.healthcareApi.domain.dto.request;

public record HealthProfessionalRequestDTO(UserRequestDTO user, Integer type, Integer specialty, String professionalId) {
}
