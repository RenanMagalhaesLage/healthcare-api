package com.healthcareApi.domain.dto.request;

public record HealthProfessionalRequestDTO(UserRequestDTO user, Long healthProfessionalId, Integer type, Integer specialty, String professionalId) {
}
