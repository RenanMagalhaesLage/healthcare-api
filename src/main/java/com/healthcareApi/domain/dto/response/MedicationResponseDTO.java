package com.healthcareApi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicationResponseDTO {
    private Long id;
    private String name;
    private String dosage;
    private String phone;
    private String frequency;
}
