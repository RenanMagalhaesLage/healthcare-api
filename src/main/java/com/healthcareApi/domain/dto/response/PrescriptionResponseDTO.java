package com.healthcareApi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrescriptionResponseDTO {
    private Long id;
    private String observation;
    private List<MedicationResponseDTO> medications;
}
