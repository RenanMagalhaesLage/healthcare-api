package com.healthcareApi.domain.dto.response;

import com.healthcareApi.enums.GenderEnum;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HealthProfessionalResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private ProfessionalTypeEnum type;
    private SpecialtyEnum specialty;
    private String professionalId;
}
