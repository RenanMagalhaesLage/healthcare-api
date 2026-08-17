package com.healthcareApi.domain.dto.request;

import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;

public record HealthProfessionalRequestDTO(UserRequestDTO user, Long healthProfessionalId, ProfessionalTypeEnum type, SpecialtyEnum specialty, String professionalId) {
}
