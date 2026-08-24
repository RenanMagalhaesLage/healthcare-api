package com.healthcareApi.domain.dto.response;

import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String bloodType;
    private BigDecimal height;
    private BigDecimal weight;
}
