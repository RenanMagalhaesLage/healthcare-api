package com.healthcareApi.domain.dto.response;

import com.healthcareApi.domain.entity.HealthProfessionalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalCenterResponseDTO {
    private Long id;
    private String name;
    private AddressResponseDTO address;
    private String phone;
    private Set<HealthProfessionalEntity> healthProfessionalSet;
}
