package com.healthcareApi.domain.dto.response;

import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import com.healthcareApi.enums.ProfessionalTypeEnum;
import com.healthcareApi.enums.SpecialtyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HealthProfessionalMedicalCenterResponseDTO {
    private MedicalCenterResponseDTO medicalCenter;
    private Set<HealthProfessionalEntity> healthProfessionalList;
}
